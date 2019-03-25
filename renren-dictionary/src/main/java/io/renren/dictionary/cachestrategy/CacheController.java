package io.renren.dictionary.cachestrategy;

import io.renren.dictionary.config.DictionaryProperties;
import io.renren.dictionary.constants.CacheType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
public class CacheController implements ApplicationContextAware {

    private ApplicationContext context;

    private ICacheHandler cacheHandler;

    @Autowired
    private DictionaryProperties properties;

    @PostConstruct
    private void init() {
        CacheType cacheType = properties.getCacheType();
        System.out.println("数据字典加载方式：" + cacheType);
        // 拼接成需要使用的bean名称
        String beanName = StringUtils.lowerCase(cacheType.toString()) + ICacheHandler.CACHE_TYPE_SUFFIX;
        cacheHandler = context.getBean(beanName, ICacheHandler.class);
    }

    public void set(String key, Object value) {
        cacheHandler.set(key, value);
    }

    public List get(String key) {
        return (List) cacheHandler.get(key);
    }

    public void reset(String key, Object value) {
        cacheHandler.reset(key, value);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
