package io.renren.common.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.renren.common.service.DictCacheService;
import io.renren.common.utils.RedisUtils;
import io.renren.modules.sys.entity.SysDictEntity;
import io.renren.modules.sys.service.SysDictService;

@Component
public class DictComponent {
    @Autowired
    private SysDictService sysDictService;	
	
    @Autowired
    private RedisUtils redisUtils;
	
    public static DictComponent dictComponent;
    
    //是否开启redis缓存  true开启   false关闭
    @Value("${renren.redis.open}")
    private boolean open;

    
    @Autowired
    private DictCacheService dictCacheService;
    
    @PostConstruct
    private void init() {
    	dictComponent = this;
    	dictComponent.redisUtils = this.redisUtils;
    	dictComponent.sysDictService = this.sysDictService;
    	dictComponent.open = this.open;
    }
    
    /**
     * 系统初始化加载数据字典缓存
     */
    public static void initDictCacheData() {
    	List<SysDictEntity> typeList = dictComponent.sysDictService.getSysDictEntityGroupByType();
    	if(dictComponent.open == true) {
    		loadDictDataToRedis(typeList);
    	} else {
    		loadDictDataToMap(typeList);
    	}
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
			insertEmpty(dictList, typeList.get(i));
			dictComponent.redisUtils.set(type, dictList);
		}
    }
    
    /**
     * 数据放入到map中
     * @param typeList
     */
    
    private static void loadDictDataToMap(List<SysDictEntity> typeList) {
		Map<String, Object> cacheMap = dictComponent.dictCacheService.getCacheMap(); 
		for(int i = 0; i < typeList.size(); i++) {
			String type = typeList.get(i).getType();
			// 根据类型查询每种数据字典，添加到map中
			List<SysDictEntity> dictList = dictComponent.sysDictService.getSysDictEntity(type);
			insertEmpty(dictList, typeList.get(i));
			cacheMap.put(type, dictList);
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
    	if(dictComponent.open == true) {
    		for(String type : arrType) {
    			resultMap.put(type, dictComponent.redisUtils.get(type, ArrayList.class));
    		}
    	} else {
    		Map<String, Object> dictMap = dictComponent.dictCacheService.getCacheMap();
    		for(String type: arrType) {
    			resultMap.put(type, dictMap.get(type));
    		}
    	}
    	return resultMap;
    }
    
    /**
     * 增、改数据字典时，更新缓存
     * @param type
     * @param dictList
     */
    public static void reloadDictCacheData(String type, List<SysDictEntity> dictList) {
    	dictComponent.redisUtils.delete(type);
    	insertEmpty(dictList, null);
    	dictComponent.redisUtils.set(type, dictList);
    }
    
    /**
     * 删除数据字典时，更新缓存
     * @param ids
     */
    public static void reloadDictCacheDataBatch(Long[] ids) {
    	List<SysDictEntity> delList = dictComponent.sysDictService.getSysDictEntityGroupByType(ids);
    	loadDictDataToRedis(delList);
    }
    
    /**
     * list第一位存放一个空值
     * @param dictList
     */
    private static void insertEmpty(List<SysDictEntity> dictList, SysDictEntity typeObject) {
    	SysDictEntity empty = new SysDictEntity();
    	empty.setCode(StringUtils.EMPTY);
    	empty.setValue(StringUtils.EMPTY);
    	dictList.add(0, empty);
    }
    
}
