package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.BteResultDao;
import io.renren.modules.sys.entity.BteResultEntity;
import io.renren.modules.sys.entity.BteResultEntityExt;
import io.renren.modules.sys.service.BteResultService;


@Service("bteResultService")
public class BteResultServiceImpl extends ServiceImpl<BteResultDao, BteResultEntity> implements BteResultService {
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BteResultEntity> page = this.selectPage(
                new Query<BteResultEntity>(params).getPage(),
                new EntityWrapper<BteResultEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public List<BteResultEntityExt> queryResultList(Integer evalId) {
		// 查询常规试题list
		List<BteResultEntityExt> resultList = baseMapper.queryLessonResultList(evalId);
		// 查询课程列表
		resultList.addAll(baseMapper.queryQuestionResultList(evalId));
		return resultList;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void insertResultBatch(Map<String, Object> resultMap) {
		List<BteResultEntity> resultList = new ArrayList<>();
		for (String keys : resultMap.keySet()) {
			Map<String, Object> map = (Map<String, Object>) resultMap.get(keys);
			resultList.add(this.makeResultEntity(map));
		}
		// 批量插入结果
		this.insertBatch(resultList);
	}
	
	/**
	 * 生成测评结果entity
	 * @param map
	 * @return
	 */
	private BteResultEntity makeResultEntity(Map<String, Object> map) {
		BteResultEntity resultEntity = new BteResultEntity();
		// 测评id
		resultEntity.setEvalId(Integer.parseInt(String.valueOf(map.get("evalId"))));
		// 测评类型
		resultEntity.setQuestionTypeId(Integer.parseInt(String.valueOf(map.get("questionTypeId"))));
		// 问题编号
		resultEntity.setQuestionId(Integer.parseInt(String.valueOf(map.get("questionId"))));
		// 类型：5 为其他建议
		if(resultEntity.getQuestionTypeId() != 5) {
			// 测评分数
			resultEntity.setQuestionScore(Integer.parseInt(String.valueOf(map.get("questionScore"))));
		} else { // 具体意见
			resultEntity.setEvalSuggest(String.valueOf(map.get("evalSuggest")));
		}
		resultEntity.setCreateDate(new Date());
		return resultEntity;
	}

}
