package io.renren.dictionary.aspect.strategy;

import io.renren.common.utils.MapUtils;
import io.renren.dictionary.annotation.DictionaryCache;
import io.renren.dictionary.component.DictComponent;
import io.renren.dictionary.constants.DictConstant;
import io.renren.dictionary.constants.DictOperation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 常规数据字典更新操作类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2019-01-30 20:00
 */
public class DictModifyHandler implements IDictModifyHandler {
    @Autowired
    DictComponent dictComponent;

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
        // 将实体类型转为map类型
        Map<String, Object> dictEntity = MapUtils.transEntity2Map(param);
        // 将该类型重新载入缓存中
        dictComponent.reloadDictCacheData((String) dictEntity.get(DictConstant.DICT_TYPE));
    }

    /**
     * OP_DELETE时，调用
     * @param param
     */
    private void dictionaryDeleteHandler(Object param) {
        Long[] ids = (Long[]) param;
        dictComponent.reloadDictCacheData(ids);
    }
}
