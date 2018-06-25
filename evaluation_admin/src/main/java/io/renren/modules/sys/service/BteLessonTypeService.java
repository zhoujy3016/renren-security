package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.BteLessonTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-25 13:29:14
 */
public interface BteLessonTypeService extends IService<BteLessonTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    /**
     * 公共类
     * @return
     */
    
    List<BteLessonTypeEntity> queryLessonTypeByCategory1();
    /**
     * 专业类
     * @return
     */
    List<BteLessonTypeEntity> queryLessonTypeByCategory2();
    
}

