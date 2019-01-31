package io.renren.dictionary.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class DictYmlCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String str = context.getEnvironment().getProperty("use-dict-yml-default");
		// 配置文件中，没有此项定义，默认为true
		if(str == null) {
			return true;
		} else {
			return str.equals("true");
		}
	}

}
