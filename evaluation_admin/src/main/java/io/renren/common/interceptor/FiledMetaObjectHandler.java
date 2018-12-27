package io.renren.common.interceptor;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * mybatis-plus 自动装填字段
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2018-12-26
 */
@Component
public class FiledMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            FiledFill filedFill = (FiledFill) metaObject.getOriginalObject();
            Map<String, Object> fillMap = filedFill.insertFill();
            // 迭代map, 将entity中的需要自动填充的字段取出，保存
            fillMap.keySet().stream().forEach(key -> {
                setFieldValByName(key, fillMap.get(key), metaObject);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            FiledFill filedFill = (FiledFill) metaObject.getOriginalObject();
            Map<String, Object> fillMap = filedFill.updateFill();
            // 迭代map, 将entity中的需要自动填充的字段取出，保存
            fillMap.keySet().stream().forEach(key -> {
                setFieldValByName(key, fillMap.get(key), metaObject);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
