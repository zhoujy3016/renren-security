package io.renren.dictionary.cachestrategy;

import io.renren.dictionary.utils.DictConstant;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
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
@DependsOn(value = {ICacheHandler.CACHE_TYPE_REDIS, ICacheHandler.CACHE_TYPE_MEMORY})
public class CacheController implements ApplicationContextAware {

    private ApplicationContext context;

    @Value("${dictionary.cache-type:memory}")
    private String cacheType;

    private ICacheHandler cacheHandler;

    @PostConstruct
    private void init() {
        System.out.println("数据字典缓存方式：" + cacheType);
        cacheHandler = context.getBean(DictConstant.CACHE_TYPE_PREFIX + cacheType, ICacheHandler.class);
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
