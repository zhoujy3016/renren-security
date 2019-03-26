package io.renren.dictionary.cachestrategy;

import io.renren.dictionary.config.DictionaryProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 根据配置文件cache类型控制类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2019-03-20 15:30
 */

@Component
public class CacheController {

    private ICacheHandler cacheHandler;

    @Autowired
    private DictionaryProperties properties;

    @Autowired
    private DefaultListableBeanFactory factory;

    @PostConstruct
    private void init() {
        // 拼接成需要使用的bean名称
        String beanName = StringUtils.lowerCase(properties.getCacheType().toString()) + ICacheHandler.CACHE_TYPE_SUFFIX;
        cacheHandler = factory.getBean(beanName, ICacheHandler.class);
        cacheHandler.clear();
    }

    public void set(String key, Object value) {
        cacheHandler.set(key, value);
    }

    public List get(String key) {
        return (List) cacheHandler.get(key);
    }

}
