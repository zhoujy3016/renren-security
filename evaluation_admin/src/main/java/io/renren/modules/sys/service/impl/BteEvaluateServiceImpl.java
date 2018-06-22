package io.renren.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.qcloud.cos.http.HttpRequest;

import io.renren.common.annotation.DataCreaterFilter;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.QrcodeUtil;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.BteEvaluateDao;
import io.renren.modules.sys.entity.BteEvalrefquestionEntity;
import io.renren.modules.sys.entity.BteEvaluateEntity;
import io.renren.modules.sys.entity.BteQuestionEntity;
import io.renren.modules.sys.entity.SysDictEntity;
import io.renren.modules.sys.service.BteEvalrefquestionService;
import io.renren.modules.sys.service.BteEvaluateService;
import io.renren.modules.sys.service.BteQuestionService;
import io.renren.modules.sys.service.SysDictService;
import io.renren.modules.sys.shiro.ShiroUtils;


@Service("bteEvaluateService")
public class BteEvaluateServiceImpl extends ServiceImpl<BteEvaluateDao, BteEvaluateEntity> implements BteEvaluateService {
	
	@Autowired
	private SysDictService sysDictService;
	
	@Autowired
	private BteQuestionService bteQuestionService;
	
	@Autowired
	private BteEvalrefquestionService bteEvalrefquestionService;
	
    @Override
    @DataCreaterFilter
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BteEvaluateEntity> page = this.selectPage(
                new Query<BteEvaluateEntity>(params).getPage(),
                new EntityWrapper<BteEvaluateEntity>()
                .where("1=1", params)
                .orderBy("data_no desc")
                .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );
        for(BteEvaluateEntity eva:page.getRecords()) {
        	EntityWrapper<SysDictEntity> ew = new EntityWrapper<>();
        	// 测评状态
        	ew.eq("type", "cpzt");
        	ew.eq("code", eva.getEvalStateId());
        	SysDictEntity sysDictEntity = sysDictService.selectOne(ew);
        	eva.setEvalStateName(sysDictEntity.getValue());
        }
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
			HttpServletResponse httpServletResponse){
		String url = httpServletRequest.getScheme() + "://" + httpServletRequest.getLocalAddr() + ":" + httpServletRequest.getServerPort() + "/eva/modules/web/index.html?evalId=" + dataNo;
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
