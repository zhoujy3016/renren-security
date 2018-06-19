package io.renren.common.aspect;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import io.renren.common.annotation.DataCreaterFilter;
import io.renren.common.utils.Constant;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.shiro.ShiroUtils;


@Aspect
@Component
public class DataCreaterFilterAspect {
	
	@Pointcut("@annotation(io.renren.common.annotation.DataCreaterFilterAspect)")
	public void dataFilterCut() {
		
	}
	
	@Before("dataFilterCut()")
	public void dataFilter(JoinPoint point) {
		MethodSignature signature = (MethodSignature) point.getSignature();
		DataCreaterFilter dataFilter = signature.getMethod().getAnnotation(DataCreaterFilter.class);
		Map params =  (Map) point.getArgs()[0];
		SysUserEntity user = ShiroUtils.getUserEntity();
		
		String tableAlias = dataFilter.tableAlias();
		if(StringUtils.isNotBlank(tableAlias)) {
			tableAlias += ".";
		}
		String conditions = null;
		// 取得权限列表id
		List<Long> roleIdList = user.getRoleIdList();
		boolean isGab = false;
		for(Long id : roleIdList) {
			if(id == 1) {
				isGab = true;
			}
		}
		if(!isGab) {	// 非公安角色，查询自己的
			conditions = tableAlias + "creater= '" + user.getUsername() + "' "; 
		}
		params.put(Constant.SQL_FILTER, conditions);
	}
		
}
