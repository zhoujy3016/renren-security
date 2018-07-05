package io.renren.modules.sys.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.TblPersonDao;
import io.renren.common.entity.SysDictEntity;
import io.renren.modules.sys.entity.TblPersonEntity;
import io.renren.modules.sys.service.SysDictService;
import io.renren.modules.sys.service.TblPersonService;


@Service("tblPersonService")
public class TblPersonServiceImpl extends ServiceImpl<TblPersonDao, TblPersonEntity> implements TblPersonService {

	@Autowired
	private SysDictService sysDictService;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	// 姓名
    	String name = (String)params.get("name");
    	// 民族
    	String nationality = (String)params.get("nationality");
    	// 地区
    	String area = (String) params.get("area");
        Page<TblPersonEntity> page = this.selectPage(
                new Query<TblPersonEntity>(params).getPage(),
                new EntityWrapper<TblPersonEntity>()
                .like(StringUtils.isNotBlank(name), "person_name", name)
                .eq(StringUtils.isNotBlank(nationality), "nationality", nationality)
                .eq(StringUtils.isNotBlank(area), "area", area)
                
        );
        
        // 设置民族名称
        for(TblPersonEntity personEntity: page.getRecords()) {
        	EntityWrapper<SysDictEntity> ew = new EntityWrapper<>();
        	ew.eq("type", "mz");
        	ew.eq("code", personEntity.getNationality());
        	SysDictEntity sysDictEntity = sysDictService.selectOne(ew);
        	personEntity.setNationalityName(sysDictEntity.getValue());
        }
        
        // 设置地区
        for(TblPersonEntity personEntity: page.getRecords()) {
        	EntityWrapper<SysDictEntity> ew = new EntityWrapper<>();
        	ew.eq("type", "area");
        	ew.eq("code", personEntity.getArea());
        	SysDictEntity sysDictEntity = sysDictService.selectOne(ew);
        	personEntity.setAreaName(sysDictEntity.getValue());
        }

        return new PageUtils(page);
    }

	@Override
	public PageUtils selectPersonList(Map<String, Object> params) {
		Page<TblPersonEntity> page = new Query<TblPersonEntity>(params).getPage();
		// 前台传入的条件，姓名，民族，地区
		page.setRecords(baseMapper.selectPersonList(page, (String)params.get("name"), 
				(String)params.get("nationality"), (String)params.get("area")));
		return new PageUtils(page);
	}



//	@Override
//	public List<TblPersonEntity> queryPersonList() {
//		List<TblPersonEntity> personList = baseMapper.queryPersonList();
//		return personList;
//	}

	

}
