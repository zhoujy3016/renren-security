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
    /** 读取配置文件， 存放key与sql语句的map */
    private Map<String, String> extraDict;
    /** mapper.xml中 namespace.id */
    private String statement;
    /** 缓存类型 */
    private CacheType cacheType = CacheType.MEMORY;

}

