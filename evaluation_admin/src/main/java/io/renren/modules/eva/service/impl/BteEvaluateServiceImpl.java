package io.renren.modules.eva.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.renren.common.annotation.DataCreatorFilter;

import io.renren.modules.eva.dao.BteEvaluateDao;
import io.renren.modules.eva.entity.BteEvalrefquestionEntity;
import io.renren.modules.eva.entity.BteEvaluateEntity;
import io.renren.modules.eva.entity.BteQuestionEntity;
import io.renren.modules.eva.service.BteEvalrefquestionService;
import io.renren.modules.eva.service.BteEvaluateService;
import io.renren.modules.eva.service.BteQuestionService;
import io.renren.modules.sys.shiro.ShiroUtils;


@Service("bteEvaluateService")
public class BteEvaluateServiceImpl extends ServiceImpl<BteEvaluateDao, BteEvaluateEntity> implements BteEvaluateService {
	@Autowired
	private BteQuestionService bteQuestionService;
	
	@Autowired
	private BteEvalrefquestionService bteEvalrefquestionService;


	/**
	 * 公网ip
 	 */
	@Value("${eva.ip}")
    private String ipAddress;

	/**
	 * 测评client端口号
	 */
	@Value("${eva.evac-port}")
    private String clientPort;
	
    @Override
    @DataCreatorFilter(tableAlias="be")
    public PageUtils queryPage(Map<String, Object> params) {
		Page<BteEvaluateEntity> page = new Query<BteEvaluateEntity>(params).getPage();
		page.setRecords(baseMapper.selectBteEvalList(page, params));
		return new PageUtils(page);
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertEvaluate(BteEvaluateEntity bteEvaluate) {
		// 查询当前开启状态的试题
		List<BteQuestionEntity> questionList = bteQuestionService.list(new QueryWrapper<BteQuestionEntity>().eq("question_state_id", "1"));
		// 创建日期
		bteEvaluate.setCreateDate(LocalDateTime.now());
		// 创建人
		bteEvaluate.setCreateUserId(ShiroUtils.getUserId());
		this.save(bteEvaluate);
		Integer evalId = bteEvaluate.getDataNo();
		
		// 测评与试题关联数据
		List<BteEvalrefquestionEntity> refList = new ArrayList<>();
		
		for(BteQuestionEntity question:questionList) {
			BteEvalrefquestionEntity ref = new BteEvalrefquestionEntity();
			ref.setEvalId(evalId);
			ref.setQuestionId(question.getDataNo());
			refList.add(ref);
		}
		// 批量插入关联数据
		this.bteEvalrefquestionService.saveBatch(refList);
	}

	@Override
	public String buildQrCode(Integer dataNo, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws Exception{
		String ipAddr = null; 
		// 有公网ip使用公网，没有使用服务器ip
		if(StringUtils.isNotBlank(ipAddress)) {
			ipAddr = ipAddress;
		} else {
			ipAddr = httpServletRequest.getLocalAddr();
		}
		// 将参数加密
		String enCode = AesUtils.Encrypt(String.valueOf(dataNo), AesUtils.CKEY);
		String url = httpServletRequest.getScheme() + "://" + ipAddr  + ":" + clientPort  + "/evac/index.html?evalId=" + enCode;
		System.out.println("url:" + url);
		return QrcodeUtil.getBase64QRCode(url, 300, 300);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void changeEvalState(Integer toState, Integer[] dataNos) {
		for(int dataNo:dataNos) {
			BteEvaluateEntity evalEntity = this.getById(dataNo);
			evalEntity.setEvalStateId(toState);
			this.updateById(evalEntity);
		}
	}

}
