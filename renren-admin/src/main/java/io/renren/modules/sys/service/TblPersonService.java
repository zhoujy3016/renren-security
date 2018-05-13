package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.TblPersonEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-26 20:42:54
 */
public interface TblPersonService extends IService<TblPersonEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    List<TblPersonEntity> queryPersonList();
}

