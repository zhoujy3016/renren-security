package io.renren.dictionary.cachestrategy;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * caffeine操作实现类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2020-07-20 20:30
 */
@Component
@ConditionalOnExpression("'${dictionary.cache-type}'.equals('caffeine')")
public class CaffeineCacheHandler implements ICacheHandler<String, Object> {

    private Cache caffeineCache;

    @Override
    public void initialize() {
        caffeineCache = Caffeine.newBuilder()
                // 初始的缓存空间大小
                .initialCapacity(100)
                // 缓存的最大条数
                .maximumSize(1000)
                .build();
    }

    @Override
    public void set(String key, Object value) {
        caffeineCache.put(key, value);
    }

    @Override
    public Object get(String key) {
        return caffeineCache.getIfPresent(key);
    }

    @Override
    public Map<String, Object> getAll() {
        return caffeineCache.asMap();
    }
}
