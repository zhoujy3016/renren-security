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
     * 通过类型查询数据字典list
     * @param type
     * @return
     */
    List<Map<String, Object>> getSysDictEntity(String type);

    /**
     * 通过id数组查询数据字典（批量删除时候调用）
     * @param ids
     * @return
     */
    List<Map<String, Object>> getSysDictEntityAfterDelete(Long[] ids);
}

