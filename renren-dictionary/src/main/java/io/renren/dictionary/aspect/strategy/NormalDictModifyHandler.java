package io.renren.dictionary.aspect.strategy;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.renren.dictionary.annotation.DictionaryCache;
import io.renren.dictionary.component.CacheController;
import io.renren.dictionary.config.DictionaryProperties;
import io.renren.dictionary.constants.DictOperation;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 常规数据字典更新操作类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2019-01-30 20:00
 */
public class NormalDictModifyHandler implements IDictModifyHandler {
    @Autowired
    CacheController cacheController;

    @Autowired
    DictionaryProperties properties;

    @Override
    public void updateDictionaryCache(DictionaryCache dataFilter, Object param) {
        DictOperation operation = dataFilter.operation();
        switch (operation) {
            // 插入
            case INSERT:
            // 更新
            case UPDATE: dictionaryAddOrUpdateHandler(param);
                break;
            // 删除
            case DELETE: dictionaryDeleteHandler(param);
                break;
            default:
                break;
        }
    }

    /**
     * OP_INSERT或者OP_UPDATE时，调用
     * @param param
     */
    private void dictionaryAddOrUpdateHandler(Object param) {
        JSONObject jsonObject = JSONUtil.parseObj(param);
        // 将该类型重新载入缓存中
        cacheController.reloadDictCacheData((String) jsonObject.get(properties.getPropertyType()));
    }

    /**
     * OP_DELETE时，调用
     * @param param
     */
    private void dictionaryDeleteHandler(Object param) {
        Long[] ids = (Long[]) param;
        cacheController.reloadDictCacheData(ids);
    }
}
