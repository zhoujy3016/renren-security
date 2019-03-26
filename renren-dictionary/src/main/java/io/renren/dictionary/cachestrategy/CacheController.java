package io.renren.dictionary.cachestrategy;

import io.renren.common.utils.SpringContextUtils;
import io.renren.dictionary.config.DictionaryProperties;
import io.renren.dictionary.constants.CacheType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 *
 * 使用renren-common中的SpringContextUtils的获取bean方法
 * 由于加载顺序问题，调用getBean时applicationContext为null
 * 添加@DependsOn，保证使用该bean时，顺序正确，没有异常
 */

@Component
@DependsOn(value = "springContextUtils")
public class CacheController {

    private ICacheHandler cacheHandler;

    @Autowired
    private DictionaryProperties properties;

    @PostConstruct
    private void init() {
        CacheType cacheType = properties.getCacheType();
        System.out.println("数据字典加载方式：" + cacheType);
        // 拼接成需要使用的bean名称
        String beanName = StringUtils.lowerCase(cacheType.toString()) + ICacheHandler.CACHE_TYPE_SUFFIX;
        cacheHandler = SpringContextUtils.getBean(beanName, ICacheHandler.class);
    }

    public void set(String key, Object value) {
        cacheHandler.set(key, value);
    }

    public List get(String key) {
        return (List) cacheHandler.get(key);
    }

    @Deprecated
    public void reset(String key, Object value) {
        cacheHandler.reset(key, value);
    }

}
