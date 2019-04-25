package io.renren.modules.eva.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.modules.eva.entity.BteEvaluateEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-19 21:00:48
 */
public interface BteEvaluateDao extends BaseMapper<BteEvaluateEntity> {
	
	List<BteEvaluateEntity> selectBteEvalList(Map<String, Object> params);
	
	
}
