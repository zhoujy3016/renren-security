package io.renren.common.config;

import io.renren.common.component.DictComponent;
import io.renren.modules.sys.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据字典配置类
 */

@Configuration
public class DictCacheConfig {

	@Autowired
	private  SysDictService sysDictService;

	@Bean
	@ConditionalOnBean(SysDictService.class)
	DictComponent dictComponent() {
		DictComponent dictComponent = new DictComponent();
		dictComponent.setSysDictService(sysDictService);
		return dictComponent;
	}
}
