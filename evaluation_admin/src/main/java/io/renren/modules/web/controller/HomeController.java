package io.renren.modules.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.common.utils.R;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.eva.entity.BteEvaluateEntity;
import io.renren.modules.eva.service.BteEvaluateService;
import io.renren.modules.eva.service.BteLessonService;
import io.renren.modules.eva.service.BteQuestionService;
import io.renren.modules.eva.service.BteResultService;

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

	@RequestMapping(value = "/evalPaper/{evalId}", method = RequestMethod.GET)
	public R evalPaper(@PathVariable("evalId") Integer evalId) {
		try {
			Map<String, Object> map;
			// 查看当前测评是否开启
			BteEvaluateEntity eval = this.bteEvaluateService.selectById(evalId);
			// 当前测评状态非“进行中”
			if(eval.getEvalStateId() != 1) {
				return R.error("当前测评未开始或已结束！");
			} else {
				map = new HashMap<>(3);
				map.put("lesson", bteLessonService.queryLessonsByEvalId(evalId));
				map.put("question", beQuestionService.queryQuestionByEvalRelation(evalId));
				map.put("evalId", evalId);
				return R.ok(map);
			}
		} catch(Exception e) {
			return R.error(e.toString());
		}
	}

	@RequestMapping(value="/saveEval", method = RequestMethod.POST)
	public R saveEval(@RequestBody Map<String, Object> resultMap) {
		this.bteResultService.insertResultBatch(resultMap);
		return R.ok();
	}
}
