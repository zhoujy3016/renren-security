package io.renren.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.BteLessonDao;
import io.renren.modules.sys.entity.BteLessonEntity;
import io.renren.modules.sys.entity.SysDictEntity;
import io.renren.modules.sys.service.BteLessonService;
import io.renren.modules.sys.service.SysDictService;


@Service("bteLessonService")
public class BteLessonServiceImpl extends ServiceImpl<BteLessonDao, BteLessonEntity> implements BteLessonService {

	@Autowired
	private SysDictService sysDictService;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params, Integer evalId) {
        Page<BteLessonEntity> page = this.selectPage(
                new Query<BteLessonEntity>(params).getPage(),
                new EntityWrapper<BteLessonEntity>().eq("eval_id", evalId)
        );
        
        for(BteLessonEntity lesson:page.getRecords()) {
        	EntityWrapper<SysDictEntity> ew = new EntityWrapper<>();
        	// 课程类型
        	ew.eq("type", "kclx");
        	ew.eq("code", lesson.getLessonTypeId());
        	SysDictEntity sysDictEntity = sysDictService.selectOne(ew);
        	lesson.setLessonTypeName(sysDictEntity.getValue());
        }
        return new PageUtils(page);
    }
}
