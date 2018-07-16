package io.renren.controller;

import io.renren.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * class:
 * description：
 * author；zhoujy
 * date:2018/7/16
 */
@RestController
@RequestMapping("home")
public class EvaClientController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/evalPaper/{deCode}")
    public R evalPaper(@PathVariable("deCode") String deCode) {
        return this.restTemplate.getForObject("http://localhost:8081/home/evalPaper/" + deCode, R.class);
    }
}
