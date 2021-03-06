package io.renren.modules.eva.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.modules.eva.entity.BteLessonTypeEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-07-02 09:44:42
 */
public interface BteLessonTypeDao extends BaseMapper<BteLessonTypeEntity> {
	/**
	 * 通过分类查询类型list
	 * @param params
	 * @return
	 */
	List<BteLessonTypeEntity> queryLessonTypeList(Page page, @Param("params") Map params);
}
