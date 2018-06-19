package io.renren.common.component;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class AppRunner implements ApplicationRunner{
    
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// 加载数据字典到redis中
		DictComponent.initDictCacheData();
	}
	
}