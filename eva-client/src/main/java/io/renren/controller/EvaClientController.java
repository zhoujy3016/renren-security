package io.renren.controller;

import io.renren.common.utils.AesUtils;
import io.renren.common.utils.R;
import io.renren.service.EvaluteFeignService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * class:
 * description：
 * author；zhoujy
 * date:2018/7/16
 */
@RestController
public class EvaClientController {
    @Autowired
    private EvaluteFeignService evaluteFeignService;

    /**
     * 测评问卷显示调用
     * @param
     * @return
     */
    @GetMapping(value="/evalPaper")
    public R evalPaper(@RequestParam Map<String, Object> params) {
        try {
            // 取得加密的测评id号
            String deCode = String.valueOf(params.get("deCode"));
            // 解密
            Integer evalId = Integer.parseInt(AesUtils.Decrypt(deCode, AesUtils.CKEY));
            return evaluteFeignService.evalPaper(evalId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 保存测评问卷答案
     * @param resultMap
     * @return
     */
    @PostMapping(value="/saveEval")
    public R saveEval(@RequestBody Map<String, Object> resultMap) {
        R result =  evaluteFeignService.saveEval(resultMap);
        return result;
    }

}
