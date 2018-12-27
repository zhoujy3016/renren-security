package io.renren.common.interceptor;

import java.util.HashMap;
import java.util.Map;

/**
 * entity自动装填字段接口
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2018-12-26
 */
public interface FiledFill {

    /**
     * 插入操作的时候，需要自动装填的字段放入map中并返回
     */
    default Map<String, Object> insertFill() {
        return new HashMap<>();
    }

    /**
     * 更新操作的时候，需要自动装填的字段放入map中
     */
    default Map<String, Object> updateFill() {
        return new HashMap<>();
    }

}
