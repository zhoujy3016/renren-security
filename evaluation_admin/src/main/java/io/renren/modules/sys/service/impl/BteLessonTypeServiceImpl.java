package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.BteLessonTypeDao;
import io.renren.modules.sys.entity.BteLessonTypeEntity;
import io.renren.modules.sys.service.BteLessonTypeService;


@Service("bteLessonTypeService")
public class BteLessonTypeServiceImpl extends ServiceImpl<BteLessonTypeDao, BteLessonTypeEntity> implements BteLessonTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BteLessonTypeEntity> page = this.selectPage(
                new Query<BteLessonTypeEntity>(params).getPage(),
                new EntityWrapper<BteLessonTypeEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public List<BteLessonTypeEntity> queryLessonTypeByCategory(Integer categoryId) {
		return baseMapper.queryLessonTypeByCategory(categoryId);
	}


}
