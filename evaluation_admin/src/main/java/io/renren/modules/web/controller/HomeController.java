package io.renren.modules.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.R;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.BteEvaluateEntity;
import io.renren.modules.sys.entity.BteResultEntity;
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
	
	@RequestMapping("/evalPaper/{evalId}")
	public R evalPaper(@PathVariable("evalId") Integer evalId) {
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
	}
	
	@RequestMapping(value="/saveEval", method=RequestMethod.POST)
	public @ResponseBody R saveEval(@RequestBody Map<String, Object> resultMap) {
		this.bteResultService.insertResultBatch(resultMap);
		return R.ok();
	}
}
