package io.renren.modules.eva.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.eva.dao.BteEvalrefquestionDao;
import io.renren.modules.eva.entity.BteEvalrefquestionEntity;
import io.renren.modules.eva.service.BteEvalrefquestionService;


@Service("bteEvalrefquestionService")
public class BteEvalrefquestionServiceImpl extends ServiceImpl<BteEvalrefquestionDao, BteEvalrefquestionEntity> implements BteEvalrefquestionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BteEvalrefquestionEntity> page = this.selectPage(
                new Query<BteEvalrefquestionEntity>(params).getPage(),
                new EntityWrapper<BteEvalrefquestionEntity>()
        );

        return new PageUtils(page);
    }

}
