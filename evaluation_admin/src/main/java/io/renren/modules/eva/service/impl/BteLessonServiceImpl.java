package io.renren.modules.eva.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.eva.dao.BteLessonDao;
import io.renren.modules.eva.entity.BteLessonEntity;
import io.renren.modules.eva.service.BteLessonService;


@Service("bteLessonService")
public class BteLessonServiceImpl extends ServiceImpl<BteLessonDao, BteLessonEntity> implements BteLessonService {

    @Override
    public PageUtils queryPage(Map<String, Object> params, Integer evalId) {
		Page<BteLessonEntity> page = new Query<BteLessonEntity>(params).getPage();
		page.setRecords(baseMapper.selectBteLessonList(page, evalId));
		return new PageUtils(page);
    }

	@Override
	public List<BteLessonEntity> queryLessonsByEvalId(Integer evalId) {
		return this.baseMapper.selectList(new QueryWrapper<BteLessonEntity>().eq("eval_id", evalId));
	}
}
