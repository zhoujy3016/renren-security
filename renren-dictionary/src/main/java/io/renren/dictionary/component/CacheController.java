package io.renren.dictionary.component;

import io.renren.common.utils.OptionalUtils;
import io.renren.dictionary.cachestrategy.ICacheHandler;
import io.renren.dictionary.config.DictionaryProperties;
import io.renren.dictionary.service.ExtraDictService;
import io.renren.dictionary.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * 根据配置文件cache类型控制类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2019-03-20 15:30
 */

@Component
public class CacheController {
    @Autowired(required = false)
    private IDictService sysDictService;

    @Autowired
    private ExtraDictService extraDictService;

    @Autowired
    private ICacheHandler cacheHandler;

    @Autowired
    private DictionaryProperties properties;

    @PostConstruct
    private void init() {
        cacheHandler.initialize();
    }

    /**
     *  初始化数据字典
     */
    protected void initDictCacheData() {
        loadDictDataToCache(getMapByGroup(this.sysDictService.getAllSysDictEntity()));
    }

    /**
     *  初始化自定义数据字典
     */
    protected void initExtraDictCacheData() {
        loadExtraDictDataToCache(this.extraDictService.getExtraMap());
    }

    /**
     * 设置
     * @param key
     * @param value
     */
    protected void set(String key, Object value) {
        cacheHandler.set(key, value);
    }

    /**
     * 取得
     * @param key
     * @return
     */
    protected List get(String key) {
        Object object = cacheHandler.get(key);
        return object == null? new ArrayList(1): (List)object;
    }

    /**
     * 取得所有数据字典
     * @return
     */
    protected Map<String, Object> getAllDictData() {
        return cacheHandler.getAll();
    }

    /**
     * 通过类型和名称取得code
     * @param type
     * @param text
     * @return
     */
    protected String getCode(String type, String text) {
        List<Map<String, Object>> dictList = this.get(type);
        Map<String, Object> map = dictList.stream()
                .filter(dict -> dict.get(properties.getValue()).equals(text))
                .findAny().orElse(new HashMap<>(1));
        return (String) map.get(properties.getCode());
    }

    /**
     * 通过type与code取得text
     * @param type
     * @param code
     * @return
     */
    protected String getText(String type, String code) {
        List<Map<String, Object>> dictList = this.get(type);
        Map<String, Object> map = dictList.stream()
                .filter(dict -> dict.get(properties.getCode()).equals(code))
                .findAny().orElse(new HashMap<>(1));
        return (String) map.get(properties.getValue());
    }

    /**
     * 一个集合，通过type分组，生成一个排序的map
     * @param list
     */
    private Map<String, List<Map<String, Object>>> getMapByGroup(List<Map<String, Object>> list) {
        // 分组
        Map<String, List<Map<String, Object>>> groupMap =  list.stream()
                .collect(groupingBy(map-> (String)map.get(properties.getType())));
        // 排序
        Map<String, List<Map<String, Object>>> groupSortMap  = groupMap.keySet().stream()
                .collect(toMap(k -> k, k -> groupMap.get(k).stream()
                        .sorted(comparingInt(obj ->
                                OptionalUtils.stringToInt(String.valueOf(obj.get(properties.getSort())))))
                        .collect(toList())));
        return groupSortMap;
    }

    /**
     * 数据放到cache中
     * @param dictMapGroup
     */
    private void loadDictDataToCache(Map<String, List<Map<String, Object>>> dictMapGroup) {
        dictMapGroup.forEach((k, v) -> this.set(k, v));
    }

    /**
     * 将配置文件中针对特殊的表需要放入数据字典的map,放入cache缓存中
     */
    private void loadExtraDictDataToCache(Map<String, Object> extraMap) {
        if(extraMap != null) {
            extraMap.forEach((k, v) -> this.set(k, v));
        }
    }

    /**
     * 增、改数据字典时，更新cache
     * @param type
     */
    public void reloadDictCacheData(String type) {
        // 查询当前type中的数据字典列表
        List<Map<String, Object>> dictMapList = this.sysDictService.getSysDictEntity(type);
        this.set(type, dictMapList);
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
     * 根据传入的map, 重新加载自定义数据字典到cache中
     * @param extraMap
     */
    public void reloadExtraCacheData(Map<String, Object> extraMap) {
        this.loadExtraDictDataToCache(extraMap);
    }

}
