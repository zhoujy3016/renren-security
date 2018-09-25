package io.renren.dictionary.annotation;


import io.renren.dictionary.utils.DictConstant;

import java.lang.annotation.*;

/**
 * 数据字典注解
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2018-07-12 22:00
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DictionaryCache {

	// 需要更新的数据字典key
	String dictKey() default "";
	// 操作：增、删、改
	DictConstant.DictOperation operation() default DictConstant.DictOperation.OP_INSERT;
	// 数据字典：常规、额外
	DictConstant.DictOperation dictType()  default DictConstant.DictOperation.T_NORMAL;

}
