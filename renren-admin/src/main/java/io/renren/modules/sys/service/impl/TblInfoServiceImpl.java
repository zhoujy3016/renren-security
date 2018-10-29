package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.sys.entity.SysDictEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.TblInfoDao;
import io.renren.modules.sys.entity.TblInfoEntity;
import io.renren.modules.sys.service.SysDictService;
import io.renren.modules.sys.service.TblInfoService;


@Service("tblInfoService")
public class TblInfoServiceImpl extends ServiceImpl<TblInfoDao, TblInfoEntity> implements TblInfoService {

	@Autowired
	private SysDictService sysDictService;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	// 标题
    	String title = (String) params.get("title");
    	// 新闻类型
    	String type = (String) params.get("type");
    	
        Page<TblInfoEntity> page = (Page<TblInfoEntity>) this.baseMapper.selectPage(
                new Query<TblInfoEntity>(params).getPage(),
                new QueryWrapper<TblInfoEntity>()
                .like(StringUtils.isNotBlank(title) , "info_title", title)
                .eq(StringUtils.isNotBlank(type), "info_type", type)
                .orderByDesc("info_id")
        );

        for(TblInfoEntity tblInfoEntity:page.getRecords()) {
        	QueryWrapper<SysDictEntity> ew = new QueryWrapper<>();
        	ew.eq("type", "xwlx");
        	ew.eq("code", tblInfoEntity.getInfoType());
        	SysDictEntity sysDictEntity = sysDictService.getOne(ew);
        	tblInfoEntity.setInfoTypeName(sysDictEntity.getValue());
        }
        
        return new PageUtils(page);
    }

	@Override
	public void saveInfo(TblInfoEntity infoEntity) {
		infoEntity.setInfoCreateTime(LocalDateTime.now());
		this.save(infoEntity);
		
	}

}
