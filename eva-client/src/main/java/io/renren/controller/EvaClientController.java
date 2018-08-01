package io.renren.controller;

import io.renren.common.utils.AesUtils;
import io.renren.common.utils.R;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


/**
 * class:
 * description：
 * author；zhoujy
 * date:2018/7/16
 */
@RestController
public class EvaClientController {

    // 公网ip
    @Value("${eva.ip}")
    private String ipAddress;

    @Value("${eva.port}")
    private String port;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 测评问卷显示调用
     * @param
     * @return
     */
    @GetMapping(value="/evalPaper")
    public R evalPaper(@RequestParam Map<String, Object> params, HttpServletRequest request) {

        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("STORES");
        System.out.println(serviceInstanceList.size());
        try {
            String deCode = String.valueOf(params.get("deCode"));
            String evalId = AesUtils.Decrypt(deCode, AesUtils.CKEY);
            return this.restTemplate.getForObject(request.getScheme() + "://" + ipAddress + ":"+ port +"/eva/home/evalPaper/" + evalId, R.class);
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
        this.restTemplate.postForObject(request.getScheme() + "://" + ipAddress + ":"+ port + "/eva/home/saveEval", resultMap, String.class);
        return R.ok();
    }
}
