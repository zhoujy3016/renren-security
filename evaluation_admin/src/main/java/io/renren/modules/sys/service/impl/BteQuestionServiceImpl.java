package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
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
		page.setRecords(baseMapper.selectBteQuestionList(page));
		return new PageUtils(page);
    }

	@Override
	public void insertQuestion(BteQuestionEntity bteQuestion) {
		bteQuestion.setCreateDate(new Date());
		bteQuestion.setCreateUserId(ShiroUtils.getUserId());
		this.insert(bteQuestion);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void changeQuestionStage(Integer[] dataNos) {
		for(int id:dataNos) {
			BteQuestionEntity bteQuestionEntity = this.selectById(id);
			int state = bteQuestionEntity.getQuestionStateId();
			// 切换状态
			if(state == 0) {
				state = 1;
			} else {
				state = 0;
			}
			bteQuestionEntity.setQuestionStateId(state);
			this.updateAllColumnById(bteQuestionEntity);
		}
	}

	@Override
	public List<BteQuestionEntity> queryQuestionByEvalRelation(Integer evalId) {
		// TODO Auto-generated method stub
		return null;
	}

}
