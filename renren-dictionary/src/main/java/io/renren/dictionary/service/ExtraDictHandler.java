package io.renren.dictionary.service;

import io.renren.common.exception.RRException;
import io.renren.dictionary.annotation.DictionaryCache;
import io.renren.dictionary.component.DictComponent;
import io.renren.dictionary.config.DictYmlConfig;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 其他业务表数据字典更新操作类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2019-01-30 20:00
 */
public class ExtraDictHandler implements IDictHandler {
    @Autowired
    private ExtraDictService extraDictService;

    @Autowired(required = false)
    private DictYmlConfig dictYmlConfig;

    @Autowired
    DictComponent dictComponent;

    @Override
    public void updateDictionaryCache(DictionaryCache dataFilter, Object param) {
        String[] arrKeys = dataFilter.dictKey();
        if(ArrayUtils.isEmpty(arrKeys)) {
            throw new RRException("数据字典参数为NULL，请指定key");
        }
        // 配置文件中取得sql
        Map<String, String> sqlMap = dictYmlConfig.getExtraDict();
        Map<String, Object> extraMap  = Arrays.stream(arrKeys)
                .map(String::trim)
                .collect(Collectors.toMap(key -> key, key -> extraDictService.executeQuery(sqlMap.get(key))));
        dictComponent.reloadExtraCacheData(extraMap);
    }
}
