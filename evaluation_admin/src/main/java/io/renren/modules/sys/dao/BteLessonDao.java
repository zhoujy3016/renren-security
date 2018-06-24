package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.BteLessonEntity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-20 09:55:48
 */
public interface BteLessonDao extends BaseMapper<BteLessonEntity> {
	List<BteLessonEntity> selectBteLessonList(Pagination page, @Param("evalId") Integer evalId);
}
