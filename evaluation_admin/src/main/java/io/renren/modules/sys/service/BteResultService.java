package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.BteResultEntity;
import io.renren.modules.sys.entity.BteResultEntityExt;

import java.util.List;
import java.util.Map;

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
}

