package io.renren.common.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
    
    @PostConstruct
    public void init() {
    	dictComponent = this;
    	dictComponent.redisUtils = this.redisUtils;
    	dictComponent.sysDictService = this.sysDictService;
    }
    
    /**
     * 系统初始化加载数据字典缓存
     */
    public static void initDictCacheData() {
    	List<SysDictEntity> typeList = dictComponent.sysDictService.getSysDictEntityGroupByType();
    	loadDictDataByType(typeList);
    }
    
    
    public static void loadDictDataByType(List<SysDictEntity> typeList) {
		for(int i = 0; i < typeList.size(); i++) {
			String type = typeList.get(i).getType();
			// 根据类型查询每种数据字典，添加到map中
			List<SysDictEntity> dictList = dictComponent.sysDictService.getSysDictEntity(type);
			dictComponent.redisUtils.set(type, dictList);
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
			resultMap.put(type, dictComponent.redisUtils.get(type, ArrayList.class));
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
    	dictComponent.redisUtils.set(type, dictList);
    }
    
    /**
     * 删除数据字典时，更新缓存
     * @param ids
     */
    public static void reloadDictCacheDataBatch(Long[] ids) {
    	List<SysDictEntity> delList = dictComponent.sysDictService.getSysDictEntityGroupByType(ids);
    	loadDictDataByType(delList);
    }
    
}
