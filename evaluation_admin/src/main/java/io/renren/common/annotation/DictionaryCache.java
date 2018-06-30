package io.renren.common.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DictionaryCache {

	// 需要更新的数据字典key
	String dictKey() default "";
	// 操作：增、删、改
	String operation() default "";
	// 数据字典：常规、额外
	String dictType()  default "normal";

}
