package io.renren.common.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.renren.modules.sys.entity.SysDictEntity;

@Configuration
public class DictConfig {
	
	private Map<String, List<SysDictEntity>> dictMap;
	
	public Map<String, List<SysDictEntity>> getDictMap() {
		return dictMap;
	}

	public void setDictMap(Map<String, List<SysDictEntity>> dictMap) {
		this.dictMap = dictMap;
	}

	@Bean
	public DictConfig dictConfigBean() {
		DictConfig dictConfigBean = new DictConfig();
		Map<String, List<SysDictEntity>> dictMap = new HashMap<String, List<SysDictEntity>>();
		dictConfigBean.setDictMap(dictMap);
		return dictConfigBean;
	}
}
