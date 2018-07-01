package io.renren.common.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.renren.common.service.ExtraDictService;
import io.renren.common.utils.RedisUtils;
import io.renren.modules.sys.entity.SysDictEntity;
import io.renren.modules.sys.service.SysDictService;

@Component
public class DictComponent {
    @Autowired
    private SysDictService sysDictService;	
	
    @Autowired
    private RedisUtils redisUtils;
	
    @Autowired
    private ExtraDictService extraDictService;
    
    public static DictComponent dictComponent;
    
    
    
    @PostConstruct
    private void init() {
    	dictComponent = this;
    	dictComponent.redisUtils = this.redisUtils;
    	dictComponent.sysDictService = this.sysDictService;
    }
    
    /**
     * 系统初始化加载数据字典缓存
     */
    public static void initDictCacheData() {
    	List<SysDictEntity> typeList = dictComponent.sysDictService.getSysDictEntityGroupByType();
    	loadDictDataToRedis(typeList);
		// 额外的数据字典载入到redis
		loadExtraDictDataToRedis();
    }
    
    /**
     * 数据放到redis数据库中
     * @param typeList
     */
    private static void loadDictDataToRedis(List<SysDictEntity> typeList) {
		for(int i = 0; i < typeList.size(); i++) {
			String type = typeList.get(i).getType();
			// 根据类型查询每种数据字典，添加到map中
			List<SysDictEntity> dictList = dictComponent.sysDictService.getSysDictEntity(type);
			insertEmpty(dictList);
			dictComponent.redisUtils.set(type, dictList, RedisUtils.NOT_EXPIRE);
		}
    }
    
    /**
     * 通过types从redis缓存中查出数据放入一个map中
     * @param types
     * @return
     */
    public static Map<String, Object> getDictCacheDataByTypes(String types) {
    	Map<String, Object> resultMap = new HashMap<>();
    	String[] arrType = types.split(",");
		for(String type : arrType) {
			type = type.trim();
			resultMap.put(type, dictComponent.redisUtils.get(type, ArrayList.class));
		}
    	return resultMap;
    }
    
    /**
     * 增、改数据字典时，更新缓存
     * @param type
     */
    public static void reloadDictCacheData(String type) {
    	// 查询当前type中的数据字典列表
		List<SysDictEntity> dictList = dictComponent.sysDictService.getSysDictEntity(type);
		// redis中删除
    	dictComponent.redisUtils.delete(type);
    	insertEmpty(dictList);
    	// 重新载入到redis中
    	dictComponent.redisUtils.set(type, dictList, RedisUtils.NOT_EXPIRE);
    }
    
    /**
     * 删除数据字典时，更新缓存
     * @param ids
     */
    public static void reloadDictCacheData(Long[] ids) {
    	List<SysDictEntity> delTypeList = dictComponent.sysDictService.getSysDictEntityGroupByType(ids);
    	loadDictDataToRedis(delTypeList);
    }

    /**
     * list第一位存放一个空值
     * @param dictList
     */
    private static void insertEmpty(List<SysDictEntity> dictList) {
    	SysDictEntity empty = new SysDictEntity();
    	empty.setCode(StringUtils.EMPTY);
    	empty.setValue(StringUtils.EMPTY);
    	dictList.add(0, empty);
    }
    
    /**
     * 将配置文件中针对特殊的表需要放入数据字典的map,放入redis 数据字典缓存中
     */
    private static void loadExtraDictDataToRedis() {
    	Map<String, Object> extrMap = dictComponent.extraDictService.getExtraMap();
    	for (String keys : extrMap.keySet()) {
			List<SysDictEntity> dictList = (List<SysDictEntity>) extrMap.get(keys);
	    	insertEmpty(dictList);
	    	dictComponent.redisUtils.set(keys, dictList, RedisUtils.NOT_EXPIRE);
		}
    }
    
}
