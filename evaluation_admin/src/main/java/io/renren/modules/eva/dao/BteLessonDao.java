package io.renren.modules.eva.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.modules.eva.entity.BteLessonEntity;

import java.util.List;

import org.apache.ibatis.annotations.Param;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-20 09:55:48
 */
public interface BteLessonDao extends BaseMapper<BteLessonEntity> {
	List<BteLessonEntity> selectBteLessonList(Page page, @Param("evalId") Integer evalId);
}
