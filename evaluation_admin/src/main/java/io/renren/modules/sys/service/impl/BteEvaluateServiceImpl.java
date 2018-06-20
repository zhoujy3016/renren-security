package io.renren.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.common.annotation.DataCreaterFilter;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.BteEvaluateDao;
import io.renren.modules.sys.entity.BteEvaluateEntity;
import io.renren.modules.sys.entity.SysDictEntity;
import io.renren.modules.sys.service.BteEvaluateService;
import io.renren.modules.sys.service.SysDictService;
import io.renren.modules.sys.shiro.ShiroUtils;


@Service("bteEvaluateService")
public class BteEvaluateServiceImpl extends ServiceImpl<BteEvaluateDao, BteEvaluateEntity> implements BteEvaluateService {

	@Autowired
	private SysDictService sysDictService;
	
    @Override
    @DataCreaterFilter
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BteEvaluateEntity> page = this.selectPage(
                new Query<BteEvaluateEntity>(params).getPage(),
                new EntityWrapper<BteEvaluateEntity>()
                .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );
        for(BteEvaluateEntity eva:page.getRecords()) {
        	EntityWrapper<SysDictEntity> ew = new EntityWrapper<>();
        	// 测评状态
        	ew.eq("type", "cpzt");
        	ew.eq("code", eva.getEvalStateId());
        	SysDictEntity sysDictEntity = sysDictService.selectOne(ew);
        	eva.setEvalStateName(sysDictEntity.getValue());
        }
        return new PageUtils(page);
    }

	@Override
	public void insertEvaluate(BteEvaluateEntity bteEvaluate) {
		bteEvaluate.setCreateDate(new Date());
		bteEvaluate.setCreateUserId(ShiroUtils.getUserId());
		this.insert(bteEvaluate);
	}

}
