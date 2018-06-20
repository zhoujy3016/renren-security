package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.BteQuestionEntity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-19 15:11:26
 */
public interface BteQuestionDao extends BaseMapper<BteQuestionEntity> {
	
	List<BteQuestionEntity> selectBteQuestionList(Pagination page);
	
	/**
	 * 通过evalId从关联表中查询测评的试题
	 * @param evalId
	 * @return
	 */
	List<BteQuestionEntity> selectBteQuestionHistory(@Param("evalId") Integer evalId);
}
