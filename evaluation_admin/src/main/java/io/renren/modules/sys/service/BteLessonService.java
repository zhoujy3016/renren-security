package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.BteLessonEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-20 09:55:48
 */
public interface BteLessonService extends IService<BteLessonEntity> {

    PageUtils queryPage(Map<String, Object> params, Integer evalId);
    
}

