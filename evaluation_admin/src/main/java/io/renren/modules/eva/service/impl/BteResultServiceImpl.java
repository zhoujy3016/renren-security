package io.renren.modules.eva.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.renren.modules.eva.dao.BteResultDao;
import io.renren.modules.eva.entity.BteEvaluateEntity;
import io.renren.modules.eva.entity.BteResultEntity;
import io.renren.modules.eva.entity.BteResultEntityExt;
import io.renren.modules.eva.entity.BteResultEntitySuggest;
import io.renren.modules.eva.service.BteEvaluateService;
import io.renren.modules.eva.service.BteResultService;


@Service("bteResultService")
public class BteResultServiceImpl extends ServiceImpl<BteResultDao, BteResultEntity> implements BteResultService {
	
	@Autowired
	private BteEvaluateService bteEvaluateService;

	@Autowired
	private HttpServletResponse response;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BteResultEntity> page = (Page<BteResultEntity>) this.baseMapper.selectPage(
                new Query<BteResultEntity>(params).getPage(),
                new QueryWrapper<BteResultEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public List<BteResultEntityExt> queryResultList(Integer evalId) {
		// 查询课程列表
		List<BteResultEntityExt> resultList = baseMapper.queryLessonResultList(evalId);
		// 查询常规试题list
		resultList.addAll(baseMapper.queryQuestionResultList(evalId));
		return resultList;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void insertResultBatch(Map<String, Object> resultMap) {
		List<BteResultEntity> resultList = new ArrayList<>();
		resultMap.forEach((k, v) -> resultList.add(this.makeResultEntity((Map<String, Object>)v)));
		// 批量插入结果
		this.saveBatch(resultList);
	}
	
	/**
	 * 生成测评结果entity
	 * @param map
	 * @return
	 */
	private BteResultEntity makeResultEntity(Map<String, Object> map) {
		BteResultEntity resultEntity = new BteResultEntity();
		// 测评id
		resultEntity.setEvalId(OptionalUtils.stringToInt(String.valueOf(map.get("evalId"))));
		// 测评类型
		resultEntity.setQuestionTypeId(OptionalUtils.stringToInt(String.valueOf(map.get("questionTypeId"))));
		// 问题编号
		resultEntity.setQuestionId(OptionalUtils.stringToInt(String.valueOf(map.get("questionId"))));
		// 类型：4 为其他建议
		if(resultEntity.getQuestionTypeId() != 4) {
			// 测评分数
			resultEntity.setQuestionScore(OptionalUtils.stringToInt(String.valueOf(map.get("questionScore"))));
		} else { // 具体意见
			resultEntity.setEvalSuggest(String.valueOf(map.get("evalSuggest")));
		}
		// 客户端ip
		resultEntity.setIpAddr(String.valueOf(map.get("ipAddr")));
		return resultEntity;
	}

	@Override
	public void exportResult(Integer evalId) {
		// 测评信息
		BteEvaluateEntity evalEntity = bteEvaluateService.getById(evalId);
		String excelName = evalEntity.getEvalTitle() + "结果";
		List<BteResultEntityExt> resultList = this.queryResultList(evalId);
		ExcelUtils.exportExcel(resultList, excelName, "测评结果", BteResultEntityExt.class, excelName + ".xls", response);
	}

	@Override
	public void exportSuggest(Integer evalId) {
		// 测评信息
		BteEvaluateEntity evalEntity = bteEvaluateService.getById(evalId);
		String excelName = evalEntity.getEvalTitle() + "建议";
		List<BteResultEntitySuggest> resultList = this.baseMapper.querySuggestList(evalId);
		ExcelUtils.exportExcel(resultList, excelName, "其他建议", BteResultEntitySuggest.class, excelName + ".xls", response);
	}

}
