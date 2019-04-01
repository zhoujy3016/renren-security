package io.renren.dictionary.component;

import io.renren.dictionary.cachestrategy.CacheController;
import io.renren.dictionary.config.DictionaryProperties;
import io.renren.dictionary.service.ExtraDictService;
import io.renren.dictionary.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据字典缓存操作类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2018-07-12 22:00
 */
@Component
public class DictComponent {
    @Autowired
    private IDictService sysDictService;

    @Autowired
    private ExtraDictService extraDictService;

    @Autowired
    private CacheController cacheController;

    @Autowired
    private DictionaryProperties dictionaryProperties;

	/**
     * 系统初始化加载数据字典缓存
     */
	protected void initDictCacheData() {
		// 数据字典载入到cache
		loadDictDataToCache(getMapByGroup(this.sysDictService.getAllSysDictEntity()));
		// 自定义的数据字典载入到cache
		loadExtraDictDataToCache(this.extraDictService.getExtraMap());
    }

	/**
	 * 一个集合，通过type分组，生成一个map
	 * @param list
	 */
    private Map<String, List<Map<String, Object>>> getMapByGroup(List<Map<String, Object>> list) {
    	return list.stream().collect(Collectors.groupingBy(map-> (String)map.get(dictionaryProperties.getType())));
	}
    
    /**
     * 数据放到cache中
     * @param dictMapGroup
     */
    private void loadDictDataToCache(Map<String, List<Map<String, Object>>> dictMapGroup) {
    	dictMapGroup.forEach((k, v) -> cacheController.set(k, v));
    }

    /**
     * 增、改数据字典时，更新cache
     * @param type
     */
    public void reloadDictCacheData(String type) {
    	// 查询当前type中的数据字典列表
		List<Map<String, Object>> dictMapList = this.sysDictService.getSysDictEntity(type);
		cacheController.set(type, dictMapList);
    }
    
    /**
     * 删除数据字典时，更新cache数据
     * @param ids
     */
    public void reloadDictCacheData(Long[] ids) {
    	// id数组查询数据字典集合并用type分组
		Map<String, List<Map<String, Object>>> dictMapGroup = this.sysDictService.getSysDictEntityAfterDelete(ids);
		loadDictDataToCache(dictMapGroup);
    }

    /**
     * 将配置文件中针对特殊的表需要放入数据字典的map,放入cache缓存中
     */
    private void loadExtraDictDataToCache(Map<String, Object> extraMap) {
    	if(extraMap != null) {
			extraMap.forEach((k, v) -> cacheController.set(k, v));
		}
    }

	/**
	 * 根据传入的map, 重新加载自定义数据字典到cache中
	 * @param extraMap
	 */
	public void reloadExtraCacheData(Map<String, Object> extraMap) {
    	this.loadExtraDictDataToCache(extraMap);
	}

	/**
	 * 通过types从缓存中查出数据放入一个map中
	 * @param types
	 * @return
	 */
	public Map<String, Object> getDictCacheDataByTypes(String types) {
		Map<String, Object> resultMap = Arrays.stream(types.split(","))
				.map(String::trim)
				.collect(Collectors.toMap(type -> type, type -> cacheController.get(type)));
		return resultMap;
	}

	/**
	 * 通过某种类型从缓存中查出一个类型的数据字典List
	 * @param type
	 * @return
	 */
	public List getDictCacheDataByType(String type) {
		return cacheController.get(type.trim());
	}


	/**
	 * 通过type与name取得code
	 * @param type
	 * @param cacheName
	 * @return
	 */
	public String getDictCacheCode(String type, String cacheName) {
		return cacheController.getCode(type.trim(), cacheName.trim());
	}

}
