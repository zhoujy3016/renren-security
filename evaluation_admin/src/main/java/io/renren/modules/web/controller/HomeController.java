package io.renren.modules.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.R;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.BteEvaluateEntity;
import io.renren.modules.sys.service.BteEvaluateService;
import io.renren.modules.sys.service.BteLessonService;
import io.renren.modules.sys.service.BteQuestionService;

@RestController
@RequestMapping("home")
public class HomeController extends AbstractController {
	
	@Autowired
	private BteQuestionService beQuestionService;
	
	@Autowired
	private BteLessonService bteLessonService;
	
	@Autowired
	private BteEvaluateService bteEvaluateService;
	
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
			return R.ok(map);
		}
	}
	
	@RequestMapping("/saveEval")
	public @ResponseBody R saveEval(@RequestParam Map<String, Object> params) {
		System.out.print("提交成功");
		
		return R.ok();
	}
}
