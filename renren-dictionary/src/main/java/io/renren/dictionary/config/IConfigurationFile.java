package io.renren.dictionary.config;

import java.util.Map;

/**
 * 配置文件接口
 * 该接口便于开发者实现自己的配置文件类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2019-01-30 20:00
 */
public interface IConfigurationFile {

    /**
     *  返回配置文件中的key与sql对应关系的map
     * @return
     */
    Map<String, String> getExtraDict();

    /**
     * 返回mybatis中sqlsession的statement
     * @return
     */
    String getStatement();
}
