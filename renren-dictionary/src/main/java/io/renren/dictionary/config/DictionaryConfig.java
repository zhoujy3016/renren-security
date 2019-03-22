package io.renren.dictionary.config;

import io.renren.dictionary.aspect.strategy.ExtraDictModifyHandler;
import io.renren.dictionary.aspect.strategy.IDictModifyHandler;
import io.renren.dictionary.aspect.strategy.NormalDictModifyHandler;
import io.renren.dictionary.service.ExtraDictService;
import org.springframework.beans.factory.annotation.Autowired;
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
	ExtraDictService extraDictService(@Autowired(required = false) DictionaryProperties properties) {
		ExtraDictService extraDictService = new ExtraDictService();
		if(properties != null) {
			extraDictService.setStatement(properties.getStatement());
			extraDictService.addAll(properties.getExtraDict());
		}
		return extraDictService;
	}

	@Lazy
	@Bean(name = IDictModifyHandler.NORMAL_DICT_MODIFY_HANDLER)
	NormalDictModifyHandler dictHandler() {
		return new NormalDictModifyHandler();
	}

	@Lazy
	@Bean(name = IDictModifyHandler.EXTRA_DICT_MODIFY_HANDLER)
	ExtraDictModifyHandler extraDictHandler() {
		return new ExtraDictModifyHandler();
	}
}
