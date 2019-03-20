package io.renren.dictionary.service;

/**
 * cache操作接口
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2019-03-20 15:30
 */
public interface ICacheHandler<K, V> {

    String CACHE_TYPE_REDIS = "cache_redis";

    String CACHE_TYPE_MEMORY = "cache_memory";

    void set(K key, V value);

    V get(K key);

    void reset(K key, V value);
}
