package io.renren.dictionary.cachestrategy;

import io.renren.dictionary.config.DictionaryProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 根据配置文件cache类型控制类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2019-03-20 15:30
 */

@Component
public class CacheController {

    @Autowired
    private ICacheHandler cacheHandler;

    @Autowired
    private DictionaryProperties properties;

    @PostConstruct
    private void init() {
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
     * @param text
     * @return
     */
    public String getCode(String type, String text) {
        List<Map<String, Object>> dictList = this.get(type);
        for (Map map: dictList) {
            if(map.get(properties.getValue()).equals(text)) {
                return (String) map.get(properties.getCode());
            }
        }
        return "";
    }

    /**
     * 通过type与code取得text
     * @param type
     * @param code
     * @return
     */
    public String getText(String type, String code) {
        List<Map<String, Object>> dictList = this.get(type);
        for (Map map: dictList) {
            if(map.get(properties.getCode()).equals(code)) {
                return (String) map.get(properties.getValue());
            }
        }
        return "";
    }
}
