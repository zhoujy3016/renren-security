package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.BteResultEntity;
import io.renren.modules.sys.entity.BteResultEntityExt;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-20 14:30:53
 */
public interface BteResultDao extends BaseMapper<BteResultEntity> {
	/**
	 * 以常规问题表为主表统计数据
	 * @param evalId
	 * @return
	 */
    List<BteResultEntityExt> queryQuestionResultList(Integer evalId);
    
    /**
     * 以课程信息表为主表统计数据
     * @param evalId
     * @return
     */
    List<BteResultEntityExt> queryLessonResultList(Integer evalId);
}
