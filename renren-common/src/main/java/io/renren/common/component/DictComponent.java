package io.renren.common.component;

import io.renren.common.entity.SysDictEntity;
import io.renren.common.service.ExtraDictService;
import io.renren.common.service.IDictService;
import io.renren.common.utils.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典缓存操作类
 */

@Component
public class DictComponent {
    @Autowired
    private IDictService sysDictService;
	
    @Autowired
    private RedisUtils redisUtils;
	
    @Autowired
    private ExtraDictService extraDictService;


	public IDictService getSysDictService() {
		return sysDictService;
	}

	public void setSysDictService(IDictService sysDictService) {
		this.sysDictService = sysDictService;
	}

	/**
     * 系统初始化加载数据字典缓存
     */
    public void initDictCacheData() {
    	List<SysDictEntity> typeList = this.sysDictService.getSysDictEntityGroupByType();
    	loadDictDataToRedis(typeList);
		// 额外的数据字典载入到redis
		loadExtraDictDataToRedis(this.extraDictService.getExtraMap());
    }
    
    /**
     * 数据放到redis数据库中
     * @param typeList
     */
    private void loadDictDataToRedis(List<SysDictEntity> typeList) {
		for(int i = 0; i < typeList.size(); i++) {
			String type = typeList.get(i).getType();
			// 根据类型查询每种数据字典，添加到map中
			List<SysDictEntity> dictList = this.sysDictService.getSysDictEntity(type);
			insertEmpty(dictList);
			this.redisUtils.set(type, dictList, RedisUtils.NOT_EXPIRE);
		}
    }
    
    /**
     * 通过types从redis缓存中查出数据放入一个map中
     * @param types
     * @return
     */
    public Map<String, Object> getDictCacheDataByTypes(String types) {
    	Map<String, Object> resultMap = new HashMap<>();
    	String[] arrType = types.split(",");
		for(String type : arrType) {
			type = type.trim();
			resultMap.put(type, this.redisUtils.get(type, ArrayList.class));
		}
    	return resultMap;
    }
    
    /**
     * 增、改数据字典时，更新缓存
     * @param type
     */
    public void reloadDictCacheData(String type) {
    	// 查询当前type中的数据字典列表
		List<SysDictEntity> dictList = this.sysDictService.getSysDictEntity(type);
		// redis中删除
		this.redisUtils.delete(type);
    	insertEmpty(dictList);
    	// 重新载入到redis中
		this.redisUtils.set(type, dictList, RedisUtils.NOT_EXPIRE);
    }
    
    /**
     * 删除数据字典时，更新缓存
     * @param ids
     */
    public void reloadDictCacheData(Long[] ids) {
    	List<SysDictEntity> delTypeList = this.sysDictService.getSysDictEntityGroupByType(ids);
    	loadDictDataToRedis(delTypeList);
    }

    /**
     * list第一位存放一个空值
     * @param dictList
     */
    private void insertEmpty(List<SysDictEntity> dictList) {
    	SysDictEntity empty = new SysDictEntity();
    	empty.setCode(StringUtils.EMPTY);
    	empty.setValue(StringUtils.EMPTY);
    	dictList.add(0, empty);
    }
    
    /**
     * 将配置文件中针对特殊的表需要放入数据字典的map,放入redis 数据字典缓存中
     */
    public void loadExtraDictDataToRedis(Map<String, Object> extraMap) {
    	for (String keys : extraMap.keySet()) {
			List<SysDictEntity> dictList = (List<SysDictEntity>) extraMap.get(keys);
	    	insertEmpty(dictList);
			this.redisUtils.set(keys, dictList, RedisUtils.NOT_EXPIRE);
		}
    }
    
}
