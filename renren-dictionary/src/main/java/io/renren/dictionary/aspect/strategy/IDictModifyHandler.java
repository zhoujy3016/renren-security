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
     * 常规数据字典更新bean名称
     */
    String DICT_MODIFY_HANDLER = "DICT_MODIFY_HANDLER";

    /**
     * 其他业务表数据字典更新bean名
     */
    String EXTRA_DICT_MODIFY_HANDLER = "EXTRA_DICT_MODIFY_HANDLER";
    /**
     * 更新数据字典缓存
     * @param dataFilter
     * @param param
     */
    void updateDictionaryCache(DictionaryCache dataFilter, Object param);
}
