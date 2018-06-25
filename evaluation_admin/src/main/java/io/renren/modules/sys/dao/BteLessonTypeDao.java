package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.BteLessonTypeEntity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-25 13:29:14
 */
public interface BteLessonTypeDao extends BaseMapper<BteLessonTypeEntity> {
	/**
	 * 通过分类查询类型list
	 * @param category
	 * @return
	 */
	List<BteLessonTypeEntity> queryLessonTypeByCategory(@Param("categoryId") Integer categoryId);
}
