package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.TblPersonEntity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-26 20:42:54
 */
public interface TblPersonDao extends BaseMapper<TblPersonEntity> {
	
	List<TblPersonEntity> queryPersonList();
	
	/**
	 * 自定义分页实现
	 * @param page
	 * @return
	 */
	List<TblPersonEntity> selectPersonList(Pagination page, @Param("name")String name, 
			@Param("nationality") String nationality, @Param("area") String area);
}
