package io.renren.common.service;


import org.springframework.stereotype.Service;

@Service
public class ExtraDictService {
	
	
	public void init() {
		System.out.println("配置文件形式加载数据字典bean初始化");
	}
	
	public ExtraDictService() {
		super();
		
	}
	
	public void destory() {
		System.out.println("配置文件形式加载数据字典bean销毁");
	}


}
