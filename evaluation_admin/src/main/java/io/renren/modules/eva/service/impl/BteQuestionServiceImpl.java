package io.renren.modules.eva.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.eva.dao.BteQuestionDao;
import io.renren.modules.eva.entity.BteQuestionEntity;
import io.renren.modules.eva.service.BteQuestionService;
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
		this.save(bteQuestion);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void changeQuestionStage(Integer[] dataNos) {
		for(int id:dataNos) {
			BteQuestionEntity bteQuestionEntity = this.getById(id);
			int state = bteQuestionEntity.getQuestionStateId();
			// 状态切换
			bteQuestionEntity.setQuestionStateId(state == 0 ? 1 : 0);
			this.updateById(bteQuestionEntity);
		}
	}

	@Override
	public List<BteQuestionEntity> queryQuestionByEvalRelation(Integer evalId) {
		return this.baseMapper.queryQuestionByEvalRelation(evalId);
	}

}
