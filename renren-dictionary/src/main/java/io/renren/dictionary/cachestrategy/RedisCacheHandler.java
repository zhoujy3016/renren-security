package io.renren.dictionary.cachestrategy;

import io.renren.common.utils.RedisUtils;
import io.renren.dictionary.constants.DictConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * redis操作实现类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2019-03-20 15:30
 */
@Component(value = ICacheHandler.CACHE_TYPE_REDIS)
public class RedisCacheHandler implements ICacheHandler<String, Object>{

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void set(String key, Object value) {
        redisUtils.set(DictConstant.getDictionaryKey(key), value, RedisUtils.NOT_EXPIRE);
    }

    @Override
    public Object get(String key) {
        return redisUtils.get(DictConstant.getDictionaryKey(key), ArrayList.class);
    }

    @Override
    public void reset(String key, Object value) {
        redisUtils.delete(DictConstant.getDictionaryKey(key));
        redisUtils.set(DictConstant.getDictionaryKey(key), value, RedisUtils.NOT_EXPIRE);
    }
}
