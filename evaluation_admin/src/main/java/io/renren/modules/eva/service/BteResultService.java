package io.renren.modules.eva.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.eva.entity.BteResultEntity;
import io.renren.modules.eva.entity.BteResultEntityExt;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-20 14:30:53
 */
public interface BteResultService extends IService<BteResultEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    List<BteResultEntityExt> queryResultList(Integer evalId);
    
    void insertResultBatch(Map<String, Object> resultMap);
    
    void exportResult(Integer evalId);
    
    void exportSuggest(Integer evalId);
}

