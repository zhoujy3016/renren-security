package io.renren.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.BteQuestionDao;
import io.renren.modules.sys.dao.BteResultDao;
import io.renren.modules.sys.entity.BteQuestionEntity;
import io.renren.modules.sys.entity.BteResultEntity;
import io.renren.modules.sys.service.BteResultService;


@Service("bteResultService")
public class BteResultServiceImpl extends ServiceImpl<BteResultDao, BteResultEntity> implements BteResultService {

	@Autowired
	private BteQuestionDao bteQuestionDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BteResultEntity> page = this.selectPage(
                new Query<BteResultEntity>(params).getPage(),
                new EntityWrapper<BteResultEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public List<BteResultEntity> queryResultList(Integer evalId) {
		List<BteResultEntity> resultList = new ArrayList<>();
		List<BteQuestionEntity> questionList = this.bteQuestionDao.selectBteQuestionHistory(evalId);
		for(BteQuestionEntity question:questionList) {
			BteResultEntity result = new BteResultEntity();
			result.setQuestionTitle(question.getQuestionTitle());
			resultList.add(result);
		}
		return resultList;
	}

}
