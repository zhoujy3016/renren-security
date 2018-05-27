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

import io.renren.modules.sys.dao.TblInfoDao;
import io.renren.modules.sys.entity.SysDictEntity;
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
    	
        Page<TblInfoEntity> page = this.selectPage(
                new Query<TblInfoEntity>(params).getPage(),
                new EntityWrapper<TblInfoEntity>()
                .like(StringUtils.isNotBlank(title) , "info_title", title)
                .eq(StringUtils.isNotBlank(type), "info_type", type)
        );

        for(TblInfoEntity tblInfoEntity:page.getRecords()) {
        	EntityWrapper<SysDictEntity> ew = new EntityWrapper<>();
        	ew.eq("type", "xwlx");
        	ew.eq("code", tblInfoEntity.getInfoType());
        	SysDictEntity sysDictEntity = sysDictService.selectOne(ew);
        	tblInfoEntity.setInfoTypeName(sysDictEntity.getValue());
        }
        
        return new PageUtils(page);
    }

}
