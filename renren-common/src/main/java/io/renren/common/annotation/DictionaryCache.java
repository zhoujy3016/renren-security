package io.renren.common.annotation;

import java.lang.annotation.*;

/**
 * 数据字典注解
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DictionaryCache {

	// 需要更新的数据字典key
	String dictKey() default "";
	// 操作：增、删、改
	DictOperation operation() default DictOperation.OP_INSERT;
	// 数据字典：常规、额外
	DictOperation dictType()  default DictOperation.T_NORMAL;

}
