package io.renren.dictionary.constants;

/**
 * 数据字典类型枚举类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2018-07-12 22:00
 */
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
