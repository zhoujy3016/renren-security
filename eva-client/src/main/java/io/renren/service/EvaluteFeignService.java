package io.renren.service;

import io.renren.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name="eva-admin", url = "http://localhost:8081/eva/home/")
public interface EvaluteFeignService {

    /**
     * 查询测评试题方法
     * @param evalId
     * @return
     */
    @RequestMapping(value = "/evalPaper/{evalId}", method = RequestMethod.GET)
    R evalPaper(@PathVariable("evalId") Integer evalId);

    /**
     * 插入测评试题方法
     * @param resultMap
     * @return
     */
    @RequestMapping(value="/saveEval", method = RequestMethod.POST)
    R saveEval(@RequestBody Map<String, Object> resultMap);

}
