package io.renren.controller;

import com.netflix.appinfo.InstanceInfo;
import io.renren.common.utils.AesUtils;
import io.renren.common.utils.EurekaServerUtils;
import io.renren.common.utils.R;
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

//    // 公网ip
//    @Value("${eva.ip}")
//    private String ipAddress;
//
//    @Value("${eva.port}")
//    private String port;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EurekaServerUtils eurekaServerUtils;

    /**
     * 测评问卷显示调用
     * @param
     * @return
     */
    @GetMapping(value="/evalPaper")
    public R evalPaper(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        try {
            // 获取服务注册中心信息
            InstanceInfo info = eurekaServerUtils.getServerInstance();
            String deCode = String.valueOf(params.get("deCode"));
            String evalId = AesUtils.Decrypt(deCode, AesUtils.CKEY);
            return this.restTemplate.getForObject(request.getScheme() + "://" + info.getIPAddr() + ":"+ info.getPort() +"/eva/home/evalPaper/" + evalId, R.class);
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
        // 获取服务注册中心信息
        InstanceInfo info = eurekaServerUtils.getServerInstance();
        this.restTemplate.postForObject(request.getScheme() + "://" + info.getIPAddr() + ":"+ info.getPort() + "/eva/home/saveEval", resultMap, String.class);
        return R.ok();
    }
}
