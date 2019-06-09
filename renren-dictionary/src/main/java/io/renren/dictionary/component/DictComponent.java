package io.renren.dictionary.component;

import io.renren.common.utils.OptionalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import static java.util.stream.Collectors.*;

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
    private CacheController cacheController;

	/**
     * 系统初始化加载数据字典缓存
     */
	public void initDictCacheData() {
		// 数据字典载入到cache
		cacheController.initDictCacheData();
    }

	/**
	 * 系统初始化加载自定义数据字典缓存
	 */
	public void initExtraDictCacheData() {
		// 自定义的数据字典载入到cache
		cacheController.initExtraDictCacheData();
	}

	/**
	 * 通过types从缓存中查出数据放入一个map中
	 * @param types
	 * @return
	 */
	public Map<String, Object> getDictCacheDataByTypes(String types) {
		Map<String, Object> resultMap = Arrays.stream(types.split(","))
				.map(String::trim)
				.collect(toMap(type -> type, type -> cacheController.get(type)));
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
	 * @param text
	 * @return
	 */
	public String getDictCacheCode(String type, String text) {
		return cacheController.getCode(type.trim(), OptionalUtils.stringTrim(text));
	}

	/**
	 * 通过type与code取得text
	 * @param type
	 * @param code
	 * @return
	 */
	public String getDictCacheText(String type, String code) {
		return cacheController.getText(type.trim(), OptionalUtils.stringTrim(code));
	}

}
