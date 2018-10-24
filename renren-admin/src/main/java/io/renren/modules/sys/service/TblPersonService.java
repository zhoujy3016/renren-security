package io.renren.modules.sys.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.TblPersonEntity;
import org.springframework.web.multipart.MultipartFile;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-26 20:42:54
 */
public interface TblPersonService extends IService<TblPersonEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
//    List<TblPersonEntity> queryPersonList();
    
    /**
     * 自定义分页实现
     * @param page
     * @return
     */
    PageUtils selectPersonList(Map<String, Object> params);


    void importUsers(MultipartFile file);

}

