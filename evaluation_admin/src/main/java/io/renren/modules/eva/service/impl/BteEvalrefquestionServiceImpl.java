package io.renren.modules.eva.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.eva.dao.BteEvalrefquestionDao;
import io.renren.modules.eva.entity.BteEvalrefquestionEntity;
import io.renren.modules.eva.service.BteEvalrefquestionService;


@Service("bteEvalrefquestionService")
public class BteEvalrefquestionServiceImpl extends ServiceImpl<BteEvalrefquestionDao, BteEvalrefquestionEntity> implements BteEvalrefquestionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BteEvalrefquestionEntity> page = (Page<BteEvalrefquestionEntity>) this.baseMapper.selectPage(
                new Query<BteEvalrefquestionEntity>(params).getPage(),
                new QueryWrapper<BteEvalrefquestionEntity>()
        );

        return new PageUtils(page);
    }

}
