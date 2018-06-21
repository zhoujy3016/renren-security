package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.BteResultDao;
import io.renren.modules.sys.entity.BteResultEntity;
import io.renren.modules.sys.entity.BteResultEntityExt;
import io.renren.modules.sys.service.BteResultService;


@Service("bteResultService")
public class BteResultServiceImpl extends ServiceImpl<BteResultDao, BteResultEntity> implements BteResultService {
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BteResultEntity> page = this.selectPage(
                new Query<BteResultEntity>(params).getPage(),
                new EntityWrapper<BteResultEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public List<BteResultEntityExt> queryResultList(Integer evalId) {
		List<BteResultEntityExt> resultList = baseMapper.queryQuestionList(evalId);

		return resultList;
	}

}
