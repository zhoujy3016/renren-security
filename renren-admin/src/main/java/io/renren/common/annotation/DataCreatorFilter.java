package io.renren.common.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataCreatorFilter {
	
	String tableAlias() default "";
	
	String userId() default "create_user_id";
}
