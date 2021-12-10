package io.renren.dictionary.annotation;


import java.lang.annotation.*;

/**
 * 数据字典值映射
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2021-12-07
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DictionaryBind {

	// 目标字段
	String target() default "";
	// 数据字典key
	String dictKey() default "";

}
