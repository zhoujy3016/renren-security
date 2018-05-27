package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.TblInfoEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-27 14:01:50
 */
public interface TblInfoService extends IService<TblInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

