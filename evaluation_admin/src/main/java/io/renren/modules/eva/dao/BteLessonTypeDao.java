package io.renren.modules.eva.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import io.renren.modules.eva.entity.BteLessonTypeEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

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
	List<BteLessonTypeEntity> queryLessonTypeList(Pagination page, @Param("params") Map params);
}
