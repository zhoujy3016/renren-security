package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.BteQuestionDao;
import io.renren.modules.sys.entity.BteQuestionEntity;
import io.renren.modules.sys.service.BteQuestionService;
import io.renren.modules.sys.shiro.ShiroUtils;


@Service("bteQuestionService")
public class BteQuestionServiceImpl extends ServiceImpl<BteQuestionDao, BteQuestionEntity> implements BteQuestionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
		Page<BteQuestionEntity> page = new Query<BteQuestionEntity>(params).getPage();
		page.setRecords(baseMapper.selectBteQuestionList());
		return new PageUtils(page);
    }

	@Override
	public void insertQuestion(BteQuestionEntity bteQuestion) {
		bteQuestion.setCreateDate(new Date());
		bteQuestion.setCreateUserId(ShiroUtils.getUserId());
		
	}

}
