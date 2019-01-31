package io.renren.dictionary.config;

import io.renren.dictionary.service.ExtraDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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

	@Autowired(required = false)
	@Qualifier("DictYmlConfig")
	private IDictionaryConfigurationFile dictYmlConfig;

	@Bean
	@ConditionalOnMissingBean
	ExtraDictService extraDictService() {
		ExtraDictService extraDictService = new ExtraDictService();
		if(dictYmlConfig != null) {
			extraDictService.setStatement(dictYmlConfig.getStatement());
			extraDictService.addAll(dictYmlConfig.getExtraDict());
		}
		return extraDictService;
	}
}
