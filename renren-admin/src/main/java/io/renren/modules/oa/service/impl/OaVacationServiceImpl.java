package io.renren.modules.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.annotation.DataCreatorFilter;
import io.renren.common.utils.Constant;
import io.renren.common.utils.J8DateUtils;
import io.renren.modules.oa.entity.ProcessEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.oa.dao.OaVacationDao;
import io.renren.modules.oa.entity.OaVacationEntity;
import io.renren.modules.oa.service.OaVacationService;
import org.springframework.transaction.annotation.Transactional;


@Service("oaVacationService")
public class OaVacationServiceImpl extends ServiceImpl<OaVacationDao, OaVacationEntity> implements OaVacationService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Override
    @DataCreatorFilter(tableAlias = "oa_vacation", userId = "user_id")
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OaVacationEntity> page = (Page<OaVacationEntity>) this.baseMapper.selectPage(
                new Query<OaVacationEntity>(params).getPage(),
                new QueryWrapper<OaVacationEntity>().apply(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );

        for(OaVacationEntity oaVacationEntity:page.getRecords()) {
            String processId = oaVacationEntity.getProcessId();
            oaVacationEntity.setTitle((String) this.runtimeService.getVariable(processId,"title"));
            oaVacationEntity.setCreateDateTime(J8DateUtils.stringToDate((String)this.runtimeService.getVariable(processId,"datetime"), J8DateUtils.DATE_TIME_PATTERN));
        }

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void startVacation(OaVacationEntity oaVacationEntity, SysUserEntity user) {
        Map<String, Object> var1 = new HashMap<>();
        var1.put("userId", user.getUserId());
        var1.put("title", user.getUsername() + "请假申请");
        var1.put("datetime", J8DateUtils.format(LocalDateTime.now(), J8DateUtils.DATE_TIME_PATTERN));
        // 启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("VacationProcess", var1);
        String processInstanceId = processInstance.getId();
        // 任务
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        Map<String, Object> var2 = new HashMap<>();
        // 指定受理人
        var2.put("managerId", "1");
        var2.put("days", oaVacationEntity.getVaDays());
        // 完成任务
        taskService.complete(task.getId(), var2);
        oaVacationEntity.setUserId(user.getUserId());
        oaVacationEntity.setProcessId(processInstanceId);
        this.save(oaVacationEntity);
    }


}
