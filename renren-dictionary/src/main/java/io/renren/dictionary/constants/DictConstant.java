package io.renren.dictionary.constants;

/**
 * 数据字典常量
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2018-09-21 09:00
 */
public class DictConstant {
    /**
     * 编号
     */
    public final static String DICT_CODE = "code";

    /**
     * 类型
     */
    public final static String DICT_TYPE = "type";

    /**
     * 数据字典配置路径&文件
     */
    public final static String DICT_CONFIG_PATH = "config/dictionary-config.yml";

    /**
     * 配置文件中prefix
     */
    public final static String DICT_PREFIX = "dictionary";

    /**
     * 自定义标签名称
     */
    public final static String TAG_CACHE = "dictCache";

    /**
     * 自定义标签属性：id
     */
    public final static String PROPERTY_ID = "id";

    /**
     * execute中environment的key
     */
    public final static String KEY_DICTLIST = "dictList";


    /**
     * 缓存类型前缀
     */
    public final static String CACHE_TYPE_PREFIX = "cache_";

    /**
     * 数据字典redis缓存中的key
     * @param key
     * @return
     */
    public static String getDictionaryKey(String key){
        return DICT_PREFIX + ":" + key;
    }

}

