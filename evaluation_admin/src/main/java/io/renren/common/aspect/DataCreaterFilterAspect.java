package io.renren.common.aspect;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.renren.common.annotation.DataCreaterFilter;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.shiro.ShiroUtils;


@Aspect
@Component
public class DataCreaterFilterAspect {

	@Pointcut("@annotation(io.renren.common.annotation.DataCreaterFilter)")
	public void dataFilterCut() {
		
	}
	
	@Before("dataFilterCut()")
	public void dataFilter(JoinPoint point) throws Throwable {
		Object params = point.getArgs()[0];
		if(params != null && params instanceof Map) {
			SysUserEntity user = ShiroUtils.getUserEntity();
			if(user.getUserId() != Constant.SUPER_ADMIN) {
				Map map =  (Map)params;
				map.put(Constant.SQL_FILTER, getSQLFilter(user, point));
			}
			return;
		}
		throw new RRException("数据权限接口，只能是Map类型参数，且不能为NULL");
	}
	
	/**
	 * 用户查看数据权限（自己创建）
	 * @param user
	 * @param point
	 * @return
	 */
	private String getSQLFilter(SysUserEntity user, JoinPoint point){
		MethodSignature signature = (MethodSignature) point.getSignature();
		DataCreaterFilter dataFilter = signature.getMethod().getAnnotation(DataCreaterFilter.class);

		String tableAlias = dataFilter.tableAlias();
		if(StringUtils.isNotBlank(tableAlias)) {
			tableAlias += ".";
		}
		Subject subject = SecurityUtils.getSubject();
        StringBuilder sqlFilter = new StringBuilder();
		if(!subject.hasRole("1")) { // 非部级权限用户，查询自己创建的数据
            sqlFilter.append(tableAlias).append(dataFilter.userId()).append("=").append(user.getUserId());
		}
		return sqlFilter.toString();
	}
		
}
