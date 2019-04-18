package io.renren.dictionary.cachestrategy;

import io.renren.dictionary.config.DictionaryProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 根据配置文件cache类型控制类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2019-03-20 15:30
 */

@Component
public class CacheController {

    private ICacheHandler cacheHandler;

    @Autowired
    private DictionaryProperties properties;

    @Autowired
    private DefaultListableBeanFactory factory;

    @PostConstruct
    private void init() {
        // 拼接成需要使用的bean名称
        String beanName = StringUtils.lowerCase(properties.getCacheType().toString()) + ICacheHandler.CACHE_TYPE_SUFFIX;
        cacheHandler = factory.getBean(beanName, ICacheHandler.class);
        cacheHandler.clear();
    }

    /**
     * 设置
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        cacheHandler.set(key, value);
    }

    /**
     * 取得
     * @param key
     * @return
     */
    public List get(String key) {
        Object object = cacheHandler.get(key);
        return object == null? new ArrayList(1): (List)object;
    }

    /**
     * 通过类型和名称取得code
     * @param type
     * @param cacheName
     * @return
     */
    public String getCode(String type, String cacheName) {
        String code = (String) this.get(type).stream()
                .filter(obj ->((Map) obj).get(properties.getValue()).equals(cacheName))
                .map(obj -> ((Map)obj).get(properties.getCode()))
                .collect(Collectors.joining());
        return code;
    }

    /**
     * 通过type与code取得text
     * @param type
     * @param code
     * @return
     */
    public String getText(String type, String code) {
        String text = (String) this.get(type).stream()
                .filter(obj ->((Map) obj).get(properties.getCode()).equals(code))
                .map(obj -> ((Map)obj).get(properties.getValue()))
                .collect(Collectors.joining());
        return text;
    }
}
