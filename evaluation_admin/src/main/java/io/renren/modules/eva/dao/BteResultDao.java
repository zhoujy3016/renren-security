package io.renren.modules.eva.dao;

import io.renren.modules.eva.entity.BteResultEntity;
import io.renren.modules.eva.entity.BteResultEntityExt;
import io.renren.modules.eva.entity.BteResultEntitySuggest;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
    List<BteResultEntityExt> queryQuestionResultList(@Param("evalId") Integer evalId);
    
    /**
     * 以课程信息表为主表统计数据
     * @param evalId
     * @return
     */
    List<BteResultEntityExt> queryLessonResultList(@Param("evalId") Integer evalId);
    
    /**
     * 查找建议结果
     * @param evalId
     * @return
     */
    List<BteResultEntitySuggest> querySuggestList(@Param("evalId") Integer evalId);
}
