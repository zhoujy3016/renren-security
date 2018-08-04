package io.renren.controller;

import com.netflix.appinfo.InstanceInfo;
import io.renren.common.utils.AesUtils;
import io.renren.common.utils.EurekaServerUtils;
import io.renren.common.utils.R;
import io.renren.service.EvaluteFeignService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate;

    @Autowired
    private EurekaServerUtils eurekaServerUtils;

    @Autowired
    private EvaluteFeignService evaluteFeignService;

    /**
     * 测评问卷显示调用
     * @param
     * @return
     */
    @GetMapping(value="/evalPaper")
    public R evalPaper(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        try {
            // 取得加密的测评id号
            String deCode = String.valueOf(params.get("deCode"));
            // 解密
            Integer evalId = Integer.parseInt(AesUtils.Decrypt(deCode, AesUtils.CKEY));
//            // 获取服务注册中心信息
//            InstanceInfo info = eurekaServerUtils.getServerInstance();
//            return this.restTemplate.getForObject(request.getScheme() + "://" + info.getIPAddr() + ":"+ info.getPort() +"/eva/home/evalPaper/{1}", R.class, evalId);
            // feign调用方式
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
    public R saveEval(@RequestBody Map<String, Object> resultMap, HttpServletRequest request) {
//        // 获取服务注册中心信息
//        InstanceInfo info = eurekaServerUtils.getServerInstance();
//        return this.restTemplate.postForObject(request.getScheme() + "://" + info.getIPAddr() + ":"+ info.getPort() + "/eva/home/saveEval", resultMap, R.class);
        // feign调用方式
        return evaluteFeignService.saveEval(resultMap);
    }
}
