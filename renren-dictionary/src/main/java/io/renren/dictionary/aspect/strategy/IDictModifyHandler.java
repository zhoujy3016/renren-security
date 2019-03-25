package io.renren.dictionary.aspect.strategy;

import io.renren.dictionary.annotation.DictionaryCache;

/**
 * 数据字典更新接口
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2019-01-30 20:00
 */
public interface IDictModifyHandler {

    /**
     * 操作类型后缀
     */
    String DICT_MODIFY_TYPE_SUFFIX = "DictModifyHandler";

    /**
     * 更新数据字典缓存
     * @param dataFilter
     * @param param
     */
    void updateDictionaryCache(DictionaryCache dataFilter, Object param);
}
