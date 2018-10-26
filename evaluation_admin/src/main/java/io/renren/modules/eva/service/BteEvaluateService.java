package io.renren.modules.eva.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.eva.entity.BteEvaluateEntity;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-19 21:00:48
 */
public interface BteEvaluateService extends IService<BteEvaluateEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    void insertEvaluate(BteEvaluateEntity bteEvaluate);
    
    String buildQrCode(Integer dataNo, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception;
    
    void changeEvalState(Integer toState, Integer[] dataNos);
}

