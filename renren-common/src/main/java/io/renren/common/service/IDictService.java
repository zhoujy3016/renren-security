/**
 * 数据字典接口
 */

package io.renren.common.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.entity.SysDictEntity;

import java.util.List;

/**
 * 数据字典
 *
 * @author Mark sunlightcs@gmail.com
 * @since 3.1.0 2018-01-27
 */
public interface IDictService extends IService<SysDictEntity> {

    /**
     * 通过类型查询数据字典list
     * @param type
     * @return
     */
    List<SysDictEntity> getSysDictEntity(String type);

    /**
     * 根据类型分组
     * @return
     */
    List<SysDictEntity> getSysDictEntityGroupByType();

    /**
     * 删除数据字典 查出待删除的id所在的type分组
     * @param ids
     * @return
     */
    List<SysDictEntity> getSysDictEntityGroupByType(Long[] ids);
}

