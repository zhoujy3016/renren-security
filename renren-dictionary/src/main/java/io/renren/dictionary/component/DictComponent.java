package io.renren.dictionary.component;

import io.renren.dictionary.service.ExtraDictService;
import io.renren.dictionary.service.IDictService;
import io.renren.dictionary.utils.DictConstant;
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

	/**
     * 系统初始化加载数据字典缓存
     */
    public void initDictCacheData() {
		// 数据字典载入到redis
		loadDictDataToRedis(getMapByGroup(this.sysDictService.getAllSysDictEntity()));
		// 额外的数据字典载入到redis
		loadExtraDictDataToRedis(this.extraDictService.getExtraMap());
    }

	/**
	 * 一个集合，通过type分组，生成一个map
	 * @param list
	 */
    private Map<String, List<Map<String, Object>>> getMapByGroup(List<Map<String, Object>> list) {
    	return list.stream().collect(Collectors.groupingBy(map-> (String)map.get(DictConstant.DICT_TYPE)));
	}
    
    /**
     * 数据放到redis数据库中
     * @param dictMapGroup
     */
    private void loadDictDataToRedis(Map<String, List<Map<String, Object>>> dictMapGroup) {
    	dictMapGroup.forEach((k, v) -> setDictMapToRedis(k, v));
    }
    
    /**
     * 通过types从redis缓存中查出数据放入一个map中
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
	 * 通过某种类型从redis缓存中查出一个类型的数据字典List
	 * @param type
	 * @return
	 */
	public List getDictCacheDataByType(String type) {
		return cacheController.get(type.trim());
	}
    
    /**
     * 增、改数据字典时，更新redis数据
     * @param type
     */
    public void reloadDictCacheData(String type) {
    	// 查询当前type中的数据字典列表
		List<Map<String, Object>> dictMapList = this.sysDictService.getSysDictEntity(type);
		setDictMapToRedis(type, dictMapList);
    }
    
    /**
     * 删除数据字典时，更新redis数据
     * @param ids
     */
    public void reloadDictCacheData(Long[] ids) {
    	// id数组查询数据字典集合并用type分组
		Map<String, List<Map<String, Object>>> dictMapGroup = this.sysDictService.getSysDictEntityAfterDelete(ids);
		loadDictDataToRedis(dictMapGroup);
    }

    /**
     * 将配置文件中针对特殊的表需要放入数据字典的map,放入redis 数据字典缓存中
     */
    private void loadExtraDictDataToRedis(Map<String, Object> extraMap) {
    	if(extraMap != null) {
			extraMap.forEach((k, v) -> cacheController.set(k, v));
		}
    }

	/**
	 * 根据传入的map, 重新加载额外的数据字典到redis中
	 * @param extraMap
	 */
	public void reloadExtraCacheData(Map<String, Object> extraMap) {
    	this.loadExtraDictDataToRedis(extraMap);
	}

	/**
	 * 根据type查询对应的数据字典，set到redis中
	 * @param type
	 */
	private void setDictMapToRedis(String type, List<Map<String, Object>> list) {
		cacheController.reset(type, list);
	}
}
