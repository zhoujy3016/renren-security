package io.renren.modules.eva.dao;

import io.renren.modules.eva.entity.BteEvaluateEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-19 21:00:48
 */
public interface BteEvaluateDao extends BaseMapper<BteEvaluateEntity> {
	
	List<BteEvaluateEntity> selectBteEvalList(Pagination page, @Param("params") Map<String, Object> params);
	
	
}
