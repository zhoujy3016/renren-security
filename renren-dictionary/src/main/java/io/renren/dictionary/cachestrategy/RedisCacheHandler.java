package io.renren.dictionary.cachestrategy;

import io.renren.common.utils.RedisUtils;
import io.renren.dictionary.constants.DictConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * redis操作实现类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2019-03-20 15:30
 */
@Component
@ConditionalOnExpression("'${dictionary.cache-type}'.equals('redis')")
public class RedisCacheHandler implements ICacheHandler<String, Object>{

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void initialize() {
        // 清空dictionary:*
        redisUtils.deleteByPrefix(DictConstant.getDictionaryKey("*"));
    }

    @Override
    public void set(String key, Object value) {
        redisUtils.set(DictConstant.getDictionaryKey(key), value, RedisUtils.NOT_EXPIRE);
    }

    @Override
    public Object get(String key) {
        return redisUtils.get(DictConstant.getDictionaryKey(key), ArrayList.class);
    }

    @Override
    public Map<String, Object> getAll() {
        List<String> keyList = new ArrayList<>(redisUtils.getKeys(DictConstant.getDictionaryKey("*")));
        return keyList.stream().map(key -> key.split(":")[1]).collect(toMap(key -> key, key-> this.get(key)));
    }
}
