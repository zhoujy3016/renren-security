package io.renren.modules.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.oa.entity.OaVacationEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import org.activiti.engine.task.Comment;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-11-19 10:18:19
 */
public interface OaVacationService extends IService<OaVacationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void startVacation(OaVacationEntity oaVacationEntity, SysUserEntity user);

    List<Comment> queryCommentInfo(OaVacationEntity oaVacationEntity);

    void updateVacation(OaVacationEntity oaVacationEntity, SysUserEntity user);

    PageUtils queryTaskPage(Map<String, Object> params, SysUserEntity user);

    OaVacationEntity getOneByProcessId(String processId);

    void vacationApprove(Map<String, Object> params);
}

