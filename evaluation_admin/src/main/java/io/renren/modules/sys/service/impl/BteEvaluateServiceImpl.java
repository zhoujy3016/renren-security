package io.renren.modules.sys.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.common.annotation.DataCreaterFilter;
import io.renren.common.utils.AesUtil;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.QrcodeUtil;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.BteEvaluateDao;
import io.renren.modules.sys.entity.BteEvalrefquestionEntity;
import io.renren.modules.sys.entity.BteEvaluateEntity;
import io.renren.modules.sys.entity.BteQuestionEntity;
import io.renren.modules.sys.service.BteEvalrefquestionService;
import io.renren.modules.sys.service.BteEvaluateService;
import io.renren.modules.sys.service.BteQuestionService;
import io.renren.modules.sys.shiro.ShiroUtils;


@Service("bteEvaluateService")
public class BteEvaluateServiceImpl extends ServiceImpl<BteEvaluateDao, BteEvaluateEntity> implements BteEvaluateService {
	@Autowired
	private BteQuestionService bteQuestionService;
	
	@Autowired
	private BteEvalrefquestionService bteEvalrefquestionService;
	

    // 公网ip
    @Value("${eva.ip}")
    public String ipAddress;
	
    @Override
    @DataCreaterFilter(tableAlias="be")
    public PageUtils queryPage(Map<String, Object> params) {
		Page<BteEvaluateEntity> page = new Query<BteEvaluateEntity>(params).getPage();
		page.setRecords(baseMapper.selectBteEvalList(page, params));
		return new PageUtils(page);
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertEvaluate(BteEvaluateEntity bteEvaluate) {
		// 查询当前开启状态的试题
		List<BteQuestionEntity> questionList = bteQuestionService.selectList(new EntityWrapper<BteQuestionEntity>().eq("question_state_id", "1"));
		// 创建日期
		bteEvaluate.setCreateDate(new Date());
		// 创建人
		bteEvaluate.setCreateUserId(ShiroUtils.getUserId());
		this.insert(bteEvaluate);
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
		this.bteEvalrefquestionService.insertBatch(refList);
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
		String enCode = AesUtil.Encrypt(String.valueOf(dataNo), AesUtil.CKEY);
		String url = httpServletRequest.getScheme() + "://" + ipAddr  + ":8001"  + "/evac/modules/web/index.html?evalId=" + enCode;
		return QrcodeUtil.getBase64QRCode(url, 300, 300);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void changeEvalStage(Integer toState, Integer[] dataNos) {
		for(int dataNo:dataNos) {
			BteEvaluateEntity evalEntity = this.selectById(dataNo);
			evalEntity.setEvalStateId(toState);
			this.updateAllColumnById(evalEntity);
		}
	}

}
