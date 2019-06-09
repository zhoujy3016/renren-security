package io.renren.dictionary.aspect.strategy;

import io.renren.common.exception.RRException;
import io.renren.dictionary.annotation.DictionaryCache;
import io.renren.dictionary.component.CacheController;
import io.renren.dictionary.config.DictionaryProperties;
import io.renren.dictionary.service.ExtraDictService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;
import static java.util.stream.Collectors.*;

/**
 * 其他业务表数据字典更新操作类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2019-01-30 20:00
 */
public class ExtraDictModifyHandler implements IDictModifyHandler {
    @Autowired
    private ExtraDictService extraDictService;

    @Autowired
    private DictionaryProperties properties;

    @Autowired
    CacheController cacheController;

    @Override
    public void updateDictionaryCache(DictionaryCache dataFilter, Object param) {
        String[] arrKeys = dataFilter.dictKey();
        if(ArrayUtils.isEmpty(arrKeys)) {
            throw new RRException("未指定重置Cache的key");
        }
        // 配置文件中取得sql
        Map<String, String> sqlMap = properties.getExtraDict();
        Map<String, Object> extraMap  = Arrays.stream(arrKeys)
                .map(String::trim)
                .collect(toMap(key -> key, key -> extraDictService.executeQuery(sqlMap.get(key))));
        cacheController.reloadExtraCacheData(extraMap);
    }
}
