package io.renren.dictionary.config;

import io.renren.dictionary.service.DictHandler;
import io.renren.dictionary.service.ExtraDictHandler;
import io.renren.dictionary.service.ExtraDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * 数据字典配置类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2018-07-12 22:00
 */
@Configuration
public class DictionaryConfig {

	@Bean
	@ConditionalOnMissingBean
	ExtraDictService extraDictService(@Autowired(required = false) DictYmlConfig dictYmlConfig) {
		ExtraDictService extraDictService = new ExtraDictService();
		if(dictYmlConfig != null) {
			extraDictService.setStatement(dictYmlConfig.getStatement());
			extraDictService.addAll(dictYmlConfig.getExtraDict());
		}
		return extraDictService;
	}

	@Bean
	@Lazy
	DictHandler dictHandler() {
		return new DictHandler();
	}

	@Bean
	@Lazy
	ExtraDictHandler extraDictHandler() {
		return new ExtraDictHandler();
	}
}
