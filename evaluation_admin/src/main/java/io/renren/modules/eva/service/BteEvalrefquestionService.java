package io.renren.modules.eva.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.eva.entity.BteEvalrefquestionEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-20 13:52:32
 */
public interface BteEvalrefquestionService extends IService<BteEvalrefquestionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

