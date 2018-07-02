package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.BteLessonTypeEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-07-02 09:44:42
 */
public interface BteLessonTypeService extends IService<BteLessonTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void insertLessonType(BteLessonTypeEntity bteLessonType);

    void updateLessonType(BteLessonTypeEntity bteLessonType);

    void deleteLessonType(Integer[] ids);
}

