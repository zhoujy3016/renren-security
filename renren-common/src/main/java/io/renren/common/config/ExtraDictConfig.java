package io.renren.common.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.renren.common.service.ExtraDictService;

/**
 * 自定义数据字典配置类
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
