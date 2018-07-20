package io.renren.modules.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.common.utils.AesUtil;
import io.renren.common.utils.R;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.BteEvaluateEntity;
import io.renren.modules.sys.service.BteEvaluateService;
import io.renren.modules.sys.service.BteLessonService;
import io.renren.modules.sys.service.BteQuestionService;
import io.renren.modules.sys.service.BteResultService;

@RestController
@RequestMapping("home")
public class HomeController extends AbstractController {
	
	@Autowired
	private BteQuestionService beQuestionService;
	
	@Autowired
	private BteLessonService bteLessonService;
	
	@Autowired
	private BteEvaluateService bteEvaluateService;
	
	@Autowired
	private BteResultService bteResultService;
	
	@GetMapping(value="/evalPaper")
	public R evalPaper(@RequestParam Map<String, Object> params) {
		try {
			String deCode = String.valueOf(params.get("deCode"));
			// 对参数进行解密
			Integer evalId = Integer.parseInt(AesUtil.Decrypt(deCode, AesUtil.CKEY));
			Map<String, Object> map;
			// 查看当前测评是否开启
			BteEvaluateEntity eval = this.bteEvaluateService.selectById(evalId);
			if(eval.getEvalStateId() != 1) { // 当前测评状态非“进行中”
				return R.error("当前测评未开始或已结束！");
			} else {
				map = new HashMap<>();
				map.put("lesson", bteLessonService.queryLessonsByEvalId(evalId));
				map.put("question", beQuestionService.queryQuestionByEvalRelation(evalId));
				map.put("evalId", evalId);
				return R.ok(map);
			}
		} catch(Exception e) {
			return R.error(e.toString());
		}
	}
	
	@RequestMapping(value="/saveEval", method=RequestMethod.POST)
	public @ResponseBody R saveEval(@RequestBody Map<String, Object> resultMap) {
		this.bteResultService.insertResultBatch(resultMap);
		return R.ok();
	}
}
