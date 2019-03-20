package io.renren.dictionary.component;

import io.renren.dictionary.service.ICacheHandler;
import io.renren.dictionary.service.MemoryCacheHandler;
import io.renren.dictionary.service.RedisCacheHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${dictionary.cache-type}")
    private String cacheType;

    private ICacheHandler cacheHandler;

    @Autowired
    private RedisCacheHandler redisCacheHandler;

    @Autowired
    private MemoryCacheHandler memoryCacheHandler;

    @PostConstruct
    private void init() {
        if(ICacheHandler.CACHE_TYPE_REDIS.equals(cacheType)) {
            cacheHandler = redisCacheHandler;
        } else if(ICacheHandler.CACHE_TYPE_MEMORY.equals(cacheType)) {
            cacheHandler = memoryCacheHandler;
        }
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

}
