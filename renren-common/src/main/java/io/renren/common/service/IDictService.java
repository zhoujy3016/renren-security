/**
 * 数据字典接口
 */

package io.renren.common.service;


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
     * 通过类型查询数据字典list
     * @param type
     * @return
     */
    List<Map<String, Object>> getSysDictEntity(String type);

    /**
     * 查询所有数据字典类型
     * @return
     */
    List<Map<String, Object>> getSysDictEntityGroupByType();

    /**
     * 通过id数组查询数据字典类型
     * @param ids
     * @return
     */
    List<Map<String, Object>> getSysDictEntityGroupByType(Long[] ids);
}

