package io.renren.common.component;

import io.renren.dictionary.component.DictComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class AppRunner implements ApplicationRunner{

	@Autowired
	private DictComponent dictComponent;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// 加载数据字典到redis中
		dictComponent.initDictCacheData();
	}
	
}
