package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.TblPersonEntity;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-26 20:42:54
 */
public interface TblPersonDao extends BaseMapper<TblPersonEntity> {
	
	List<TblPersonEntity> queryPersonList();
}
