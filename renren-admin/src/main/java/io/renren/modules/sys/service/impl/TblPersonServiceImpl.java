package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.ExcelUtils;
import io.renren.modules.sys.entity.SysDictEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.TblPersonDao;
import io.renren.modules.sys.entity.TblPersonEntity;
import io.renren.modules.sys.service.SysDictService;
import io.renren.modules.sys.service.TblPersonService;
import org.springframework.web.multipart.MultipartFile;


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
        Page<TblPersonEntity> page = (Page<TblPersonEntity>) this.baseMapper.selectPage(
                new Query<TblPersonEntity>(params).getPage(),
                new QueryWrapper<TblPersonEntity>()
                .like(StringUtils.isNotBlank(name), "person_name", name)
                .eq(StringUtils.isNotBlank(nationality), "nationality", nationality)
                .eq(StringUtils.isNotBlank(area), "area", area)
                
        );
        
        // 设置民族名称
        for(TblPersonEntity personEntity: page.getRecords()) {
        	QueryWrapper<SysDictEntity> ew = new QueryWrapper<>();
        	ew.eq("type", "mz");
        	ew.eq("code", personEntity.getNationality());
        	SysDictEntity sysDictEntity = sysDictService.getOne(ew);
        	personEntity.setNationalityName(sysDictEntity.getValue());
        }
        
        // 设置地区
        for(TblPersonEntity personEntity: page.getRecords()) {
			QueryWrapper<SysDictEntity> ew = new QueryWrapper<>();
        	ew.eq("type", "area");
        	ew.eq("code", personEntity.getArea());
        	SysDictEntity sysDictEntity = sysDictService.getOne(ew);
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


	@Override
	public void importUsers(MultipartFile file) {
		List<TblPersonEntity> personList = ExcelUtils.importExcel(file, 0, 1, TblPersonEntity.class);
		this.saveBatch(personList);
	}

//	@Override
//	public List<TblPersonEntity> queryPersonList() {
//		List<TblPersonEntity> personList = baseMapper.queryPersonList();
//		return personList;
//	}

	

}
