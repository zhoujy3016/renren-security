package io.renren.common.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.renren.common.service.ExtraDictService;
/**
 * 自定义数据字典配置类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2018-07-12 22:00
 */
@Configuration
@ConditionalOnClass(ExtraDictService.class)
@EnableAutoConfiguration
public class ExtraDictConfig {
	
	@Bean(initMethod="init", destroyMethod="destory")
	ExtraDictService extraDictService() {
		return new ExtraDictService();
	}
}
