package io.renren.common.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class DictCacheService {
	
	private Map<String, Object> cacheMap;
	
	public void init() {
		System.out.println("创建数据字典缓存bean, 并初始化map");
		cacheMap = new HashMap<String, Object>();
	}
	
	public DictCacheService() {
		super();
		
	}
	
	public void destory() {
		System.out.println("销毁数据字典缓存bean");
	}

	public Map<String, Object> getCacheMap() {
		return cacheMap;
	}

	public void setCacheMap(Map<String, Object> cacheMap) {
		this.cacheMap = cacheMap;
	}

}
