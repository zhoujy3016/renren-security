/**
 * 数据字典接口
 */

package io.renren.dictionary.service;


import java.util.List;
import java.util.Map;

/**
 * 数据字典component调用接口
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2018-07-12 22:00
 */
public interface IDictService {

    /**
     * 查询所有数据字典数据
     * @param
     * @return
     */
    List<Map<String, Object>> getAllSysDictEntity();

    /**
     * 查询某个类型下的数据字典数据
     * @param type
     * @return
     */
    List<Map<String, Object>> getSysDictEntity(String type);

    /**
     * 批量删除时候调用该方法，通过id数组查询数据字典，返回一个键为type, 值为list的map
     * 某一个类型全部删除的情况下，要将键为type, 值为null放入map中。
     * @param ids
     * @return
     */
    Map<String, List<Map<String, Object>>> getSysDictEntityAfterDelete(Long[] ids);
}

