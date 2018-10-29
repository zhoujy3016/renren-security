package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.modules.sys.entity.TblPersonEntity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	List<TblPersonEntity> selectPersonList(Page page, @Param("name")String name,
										   @Param("nationality") String nationality, @Param("area") String area);
}
