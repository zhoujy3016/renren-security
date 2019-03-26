package io.renren.dictionary.cachestrategy;

import io.renren.common.utils.RedisUtils;
import io.renren.dictionary.constants.DictConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

/**
 * redis操作实现类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2019-03-20 15:30
 */
@Component
public class RedisCacheHandler implements ICacheHandler<String, Object>{

    @Autowired
    private RedisUtils redisUtils;

    @PostConstruct
    private void init() {
        // 清空redis
        this.clear();
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
    public void clear() {
        // 清空dictionary:*
        redisUtils.deleteByPrefix(DictConstant.getDictionaryKey("*"));
    }
}
