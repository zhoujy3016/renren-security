package io.renren.common.aspect;


import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import io.renren.common.annotation.DataCreatorFilter;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.shiro.ShiroUtils;


@Aspect
@Component
public class DataCreatorFilterAspect {

	@Pointcut("@annotation(io.renren.common.annotation.DataCreatorFilter)")
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
		DataCreatorFilter dataFilter = signature.getMethod().getAnnotation(DataCreatorFilter.class);

		String tableAlias = dataFilter.tableAlias();
		if(StringUtils.isNotBlank(tableAlias)) {
			tableAlias += ".";
		}
		Subject subject = SecurityUtils.getSubject();
        StringBuilder sqlFilter = new StringBuilder();
		// 非部级权限用户，查询自己创建的数据
		if(!subject.hasRole(Constant.ROLE_MINISTERIAL)) {
            sqlFilter.append(tableAlias).append(dataFilter.userId()).append("=").append(user.getUserId());
		}
		return sqlFilter.toString();
	}
		
}
