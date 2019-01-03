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
public class FiledFillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            FiledFill filedFill = (FiledFill) metaObject.getOriginalObject();
            Map<String, Object> fillMap = filedFill.insertFill();
            setFiledVal(fillMap, metaObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            Map<String, Object> map = (Map<String, Object>) metaObject.getOriginalObject();
            FiledFill filedFill = (FiledFill)map.get("param1");
            Map<String, Object> fillMap = filedFill.updateFill();
            setFiledVal(fillMap, metaObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  将entity实现的接口保存的map迭代设置
     */
    private void setFiledVal(Map<String, Object> fillMap, MetaObject metaObject) {
        fillMap.keySet().stream().forEach(key -> {
            setFieldValByName(key, fillMap.get(key), metaObject);
        });
    }
}
