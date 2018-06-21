package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.BteQuestionEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-19 15:11:26
 */
public interface BteQuestionService extends IService<BteQuestionEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    void insertQuestion(BteQuestionEntity bteQuestion);
    
    /**
     * 改变试题状态
     * @param dataNos
     */
    void changeQuestionStage(Integer[] dataNos);
    
    /**
     * 通过evalId与关系表查询当前测评当时的试题
     * @param evalId
     * @return
     */
    List<BteQuestionEntity> queryQuestionByEvalRelation(Integer evalId);
}

