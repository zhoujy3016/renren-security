package io.renren.common.annotation;

public enum DictOperation {
    /**
     * 插入操作
     */
    OP_INSERT,
    /**
     * 更新操作
     */
    OP_UPDATE,
    /**
     * 删除操作
     */
    OP_DELETE,
    /**
     * 常规数据字典
     */
    T_NORMAL,
    /**
     * 自定义数据字典类型
     */
    T_EXTRA
}
