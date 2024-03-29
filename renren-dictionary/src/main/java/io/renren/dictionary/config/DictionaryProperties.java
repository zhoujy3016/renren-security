package io.renren.dictionary.config;

import io.renren.dictionary.constants.CacheType;
import io.renren.dictionary.constants.DictConstant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.Map;

/**
 * 数据
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2019-03-22 09:00
 */
@Configuration
@ConfigurationProperties(prefix = DictConstant.DICT_PREFIX)
@Setter
@Getter
public class DictionaryProperties implements Serializable {
    /** the map which put in dictionary type and sql */
    private Map<String, String> extraDict;
    /** namespace.id realized in mapper.xml */
    private String statement;
    /** cache type: redis or memory */
    private CacheType cacheType = CacheType.MEMORY;

    /** tableField: dictionary type */
    private String type = DictConstant.DICT_TYPE;

    /** tableField: dictionary value */
    private String value = DictConstant.DICT_VALUE;

    /** tableField: dictionary code */
    private String code = DictConstant.DICT_CODE;

    /** tableField: dictionary sort*/
    private String sort = DictConstant.DICT_SORT;

    /** property: the type's name of the entity property*/
    private String propertyType = DictConstant.DICT_TYPE;

}

