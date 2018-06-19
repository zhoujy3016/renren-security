package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.BteQuestionEntity;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-19 15:11:26
 */
public interface BteQuestionDao extends BaseMapper<BteQuestionEntity> {
	List<BteQuestionEntity> selectBteQuestionList();
}
