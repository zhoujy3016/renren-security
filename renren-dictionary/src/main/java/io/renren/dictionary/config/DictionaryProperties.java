package io.renren.dictionary.config;

import io.renren.dictionary.constants.CacheType;
import io.renren.dictionary.constants.DictConstant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * 配置文件属性
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2019-03-22 09:00
 */
@ConfigurationProperties(prefix = DictConstant.DICT_PREFIX)
@Setter
@Getter
public class DictionaryProperties implements Serializable {
    /**
     * 缓存类型
     */
    private CacheType cacheType;
}

