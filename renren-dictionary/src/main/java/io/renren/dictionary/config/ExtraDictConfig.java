package io.renren.dictionary.config;

import io.renren.dictionary.service.ExtraDictService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义数据字典配置类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2018-07-12 22:00
 */
@Configuration
@ConditionalOnClass(ExtraDictService.class)
public class ExtraDictConfig {
	
	@Bean(name = "exDictService", initMethod="init", destroyMethod="destroy")
	ExtraDictService extraDictService() {
		return new ExtraDictService();
	}
}
