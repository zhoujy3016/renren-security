package io.renren.common.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import io.renren.common.conditional.DictionaryCondition;
import io.renren.common.service.DictCacheService;

@Configuration
@ConditionalOnClass(DictCacheService.class)
@EnableAutoConfiguration
public class DictCacheConfig {
	
	@Bean(initMethod="init", destroyMethod="destory")
	@Conditional(DictionaryCondition.class)
	DictCacheService dictCacheService() {
		return new DictCacheService();
	}
}
