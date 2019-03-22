//package io.renren.dictionary.conditional;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.context.annotation.Condition;
//import org.springframework.context.annotation.ConditionContext;
//import org.springframework.core.type.AnnotatedTypeMetadata;
//
//
///**
// * 是否启用自定义配置yml条件判定condition
// *
// * @author zhoujunyi
// * @email zhoujunyi-110@163.com
// * @date 2019-01-31 20:00
// */
//public class DictYmlCondition implements Condition {
//
//	@Override
//	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//		String str = context.getEnvironment().getProperty("use-default");
//		// 配置文件中，未定义或为空，默认为true
//		return StringUtils.isBlank(str) || str.equals("true");
//	}
//
//}
