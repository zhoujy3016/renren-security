package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.BteLessonDao;
import io.renren.modules.sys.entity.BteLessonEntity;
import io.renren.modules.sys.service.BteLessonService;


@Service("bteLessonService")
public class BteLessonServiceImpl extends ServiceImpl<BteLessonDao, BteLessonEntity> implements BteLessonService {

    @Override
    public PageUtils queryPage(Map<String, Object> params, Integer evalId) {
        Page<BteLessonEntity> page = this.selectPage(
                new Query<BteLessonEntity>(params).getPage(),
                new EntityWrapper<BteLessonEntity>().eq("eval_id", evalId)
        );

        return new PageUtils(page);
    }
}
