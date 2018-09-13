package io.renren.dictionary.component;

import io.renren.common.utils.RedisUtils;
import io.renren.dictionary.service.ExtraDictService;
import io.renren.dictionary.service.IDictService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

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
		List<Map<String, Object>> typeList = this.sysDictService.getSysDictEntityGroupByType();
    	loadDictDataToRedis(typeList);
		// 额外的数据字典载入到redis
		loadExtraDictDataToRedis(this.extraDictService.getExtraMap());
    }
    
    /**
     * 数据放到redis数据库中
     * @param typeList
     */
    private void loadDictDataToRedis(List<Map<String, Object>> typeList) {
		typeList.stream().forEach(typeMap -> {
			String type = (String)typeMap.get("type");
			this.redisUtils.set(type, this.sysDictService.getSysDictEntity(type), RedisUtils.NOT_EXPIRE);
		});
    }
    
    /**
     * 通过types从redis缓存中查出数据放入一个map中
     * @param types
     * @return
     */
    public Map<String, Object> getDictCacheDataByTypes(String types) {
    	Map<String, Object> resultMap = new HashMap<>(20);
		Arrays.stream(types.split(","))
				.map(type -> type.trim())
				.forEach(type -> { resultMap.put(type, redisUtils.get(type, ArrayList.class)); });
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
     * 增、改数据字典时，更新缓存
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
     * 删除数据字典时，更新缓存
     * @param ids
     */
    public void reloadDictCacheData(Long[] ids) {
    	List<Map<String, Object>> delTypeList = this.sysDictService.getSysDictEntityGroupByType(ids);
    	loadDictDataToRedis(delTypeList);
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
    public void loadExtraDictDataToRedis(Map<String, Object> extraMap) {
    	if(extraMap != null) {
			extraMap.keySet().stream().forEach(key -> {
				this.redisUtils.set(key, extraMap.get(key), RedisUtils.NOT_EXPIRE);
			});
		}
    }

}
