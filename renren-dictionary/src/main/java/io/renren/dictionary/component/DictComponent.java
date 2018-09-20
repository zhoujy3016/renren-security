package io.renren.dictionary.component;

import io.renren.common.utils.RedisUtils;
import io.renren.dictionary.service.ExtraDictService;
import io.renren.dictionary.service.IDictService;
import org.apache.commons.lang.StringUtils;
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
    private RedisUtils redisUtils;
	
    @Autowired
    private ExtraDictService extraDictService;

	/**
     * 系统初始化加载数据字典缓存
     */
    public void initDictCacheData() {
		// 根据type分组，生成一个新的map
		Map<String, List<Map<String, Object>>> dictMapGroup = getMapByGroup(this.sysDictService.getAllSysDictEntity());
		// 数据字典载入到redis
		loadDictDataToRedis(dictMapGroup);
		// 额外的数据字典载入到redis
		loadExtraDictDataToRedis(this.extraDictService.getExtraMap());
    }

	/**
	 * 一个集合，通过type分组，生成一个map
	 * @param list
	 */
    public Map<String, List<Map<String, Object>>> getMapByGroup(List<Map<String, Object>> list) {
    	return list.stream().collect(Collectors.groupingBy(map-> (String)map.get("type")));
	}
    
    /**
     * 数据放到redis数据库中
     * @param dictMapGroup
     */
    private void loadDictDataToRedis(Map<String, List<Map<String, Object>>> dictMapGroup) {
		dictMapGroup.keySet().stream().forEach(key -> setDictMapToRedis(key, dictMapGroup.get(key)));
    }
    
    /**
     * 通过types从redis缓存中查出数据放入一个map中
     * @param types
     * @return
     */
    public Map<String, Object> getDictCacheDataByTypes(String types) {
    	Map<String, Object> resultMap = new HashMap<>(20);
		Arrays.stream(types.split(","))
				.map(String::trim)
				.forEach(type -> getDictMapFromRedis(type, resultMap));
    	return resultMap;
    }

	/**
	 * 通过某种类型从redis缓存中查出一个类型的数据字典List
	 * @param type
	 * @return
	 */
	public List getDictCacheDataByType(String type) {
		return this.redisUtils.get(type.trim(), ArrayList.class);
	}
    
    /**
     * 增、改数据字典时，更新redis数据
     * @param type
     */
    public void reloadDictCacheData(String type) {
    	// 查询当前type中的数据字典列表
		List<Map<String, Object>> dictMapList = this.sysDictService.getSysDictEntity(type);
		// redis中删除
		this.redisUtils.delete(type);
    	// 重新载入到redis中
		this.redisUtils.set(type, dictMapList, RedisUtils.NOT_EXPIRE);
    }
    
    /**
     * 删除数据字典时，更新redis数据
     * @param ids
     */
    public void reloadDictCacheData(Long[] ids) {
    	// id数组查询数据字典集合并用type分组
		Map<String, List<Map<String, Object>>> dictMapGroup = getMapByGroup(this.sysDictService.getSysDictEntityAfterDelete(ids));
		loadDictDataToRedis(dictMapGroup);
    }

    /**
     * list第一位存放一个空值
     * @param dictMapList
     */
    @Deprecated
    private void insertEmpty(List<Map<String, Object>> dictMapList) {
    	Map<String, Object> emptyMap = new HashMap<>(2);
		emptyMap.put("code", StringUtils.EMPTY);
		emptyMap.put("value", StringUtils.EMPTY);
		dictMapList.add(0, emptyMap);
    }
    
    /**
     * 将配置文件中针对特殊的表需要放入数据字典的map,放入redis 数据字典缓存中
     */
    private void loadExtraDictDataToRedis(Map<String, Object> extraMap) {
    	if(extraMap != null) {
			extraMap.keySet().stream()
                    .forEach(key -> setExtraMapToRedis(key, extraMap));
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
		this.redisUtils.set(type, list, RedisUtils.NOT_EXPIRE);
	}

	/**
	 * 根据type从redis中取得List，put到结果map中
	 * @param type
	 * @param resultMap
	 */
	private void getDictMapFromRedis(String type, Map<String, Object> resultMap) {
		List list = redisUtils.get(type, ArrayList.class);
		resultMap.put(type, list);
	}


	/**
	 * 将extra数据字典set到redis中
	 * @param key
	 * @param extraMap
	 */
	private void setExtraMapToRedis(String key, Map<String, Object> extraMap) {
		// ArrayList类型
		Object list = extraMap.get(key);
		this.redisUtils.set(key, list, RedisUtils.NOT_EXPIRE);
	}

}
