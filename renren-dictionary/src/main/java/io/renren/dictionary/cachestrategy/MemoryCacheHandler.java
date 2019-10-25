package io.renren.dictionary.cachestrategy;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import java.util.Hashtable;
import java.util.Map;

/**
 * memory操作实现类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2019-03-20 15:30
 */
@Component
@ConditionalOnExpression("'${dictionary.cache-type}'.equals('memory')")
public class MemoryCacheHandler implements ICacheHandler<String, Object> {

    private Hashtable<String, Object> cacheTable;

    @Override
    public void initialize() {
        cacheTable = new Hashtable<>();
    }

    @Override
    public void set(String key, Object value) {
        cacheTable.put(key, value);
    }

    @Override
    public Object get(String key) {
        return cacheTable.get(key);
    }

    @Override
    public Map<String, Object> getAll() {
        return cacheTable;
    }
}
