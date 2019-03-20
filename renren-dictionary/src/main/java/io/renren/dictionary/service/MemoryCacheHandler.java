package io.renren.dictionary.service;

import org.springframework.stereotype.Component;

import java.util.Hashtable;

/**
 * memory操作实现类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2019-03-20 15:30
 */
@Component(value = ICacheHandler.CACHE_TYPE_MEMORY)
public class MemoryCacheHandler implements ICacheHandler<String, Object> {

    private Hashtable<String, Object> cacheTable = new Hashtable<>();

    @Override
    public void set(String key, Object value) {
        cacheTable.put(key, value);
    }

    @Override
    public Object get(String key) {
        return cacheTable.get(key);
    }

    @Override
    public void reset(String key, Object value) {
        cacheTable.remove(key);
        cacheTable.put(key, value);
    }
}
