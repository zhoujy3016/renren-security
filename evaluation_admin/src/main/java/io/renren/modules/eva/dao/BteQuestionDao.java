package io.renren.modules.eva.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.modules.eva.entity.BteQuestionEntity;

import java.util.List;

import org.apache.ibatis.annotations.Param;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-19 15:11:26
 */
public interface BteQuestionDao extends BaseMapper<BteQuestionEntity> {
	
	List<BteQuestionEntity> selectBteQuestionList(Page page);
	
	List<BteQuestionEntity> queryQuestionByEvalRelation(@Param("evalId") Integer evalId);
}
