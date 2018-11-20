package io.renren.modules.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.annotation.DataCreatorFilter;
import io.renren.common.utils.*;

import io.renren.modules.oa.entity.TaskEntity;
import io.renren.modules.oa.utils.ActivitiUtils;
import io.renren.modules.sys.entity.SysUserEntity;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.modules.oa.dao.OaVacationDao;
import io.renren.modules.oa.entity.OaVacationEntity;
import io.renren.modules.oa.service.OaVacationService;
import org.springframework.transaction.annotation.Transactional;


@Service("oaVacationService")
public class OaVacationServiceImpl extends ServiceImpl<OaVacationDao, OaVacationEntity> implements OaVacationService {

    @Autowired
    private ActivitiUtils activitiUtils;

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
            ProcessInstance pi = activitiUtils.getProcessInstanceById(processId);
            if(pi != null) {
                oaVacationEntity.setTitle((String) this.runtimeService.getVariable(processId,"title"));
                oaVacationEntity.setCreateDateTime(J8DateUtils.stringToDate((String)this.runtimeService.getVariable(processId,"datetime"), J8DateUtils.DATE_TIME_PATTERN));
            } else {
                oaVacationEntity.setTitle("完结");
                oaVacationEntity.setCreateDateTime(LocalDateTime.now());
            }
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

    @Override
    public PageUtils queryTaskPage(Map<String, Object> params, SysUserEntity userEntity) {
        Page<Task> page = activitiUtils.getTaskListPageByAssigneeId(String.valueOf(userEntity.getUserId()),
                String.valueOf(params.get("page")),
                String.valueOf(params.get("limit")));
        List<TaskEntity> result = new ArrayList<>(page.getRecords().size());
        for(Task task: page.getRecords()) {
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setProcessId(pi.getId());
            taskEntity.setRequestDate(DateUtils.format(task.getCreateTime(), DateUtils.DATE_TIME_PATTERN));
            taskEntity.setTaskId(task.getId());
            taskEntity.setTitle((String) runtimeService.getVariable(pi.getId(), "title"));
            result.add(taskEntity);
        }

        Page<TaskEntity> pageTaskEntity = new Page(page.getCurrent(), page.getSize(), page.getTotal());
        return new PageUtils(pageTaskEntity.setRecords(result));
    }

    @Override
    public OaVacationEntity getOneByProcessId(String processId) {
        OaVacationEntity oaVacationEntity = this.getOne(new QueryWrapper<OaVacationEntity>().eq("process_id", processId));
        return oaVacationEntity;
    }

    @Override
    public void vacationApprove(Map<String, Object> params) {
        String processId = (String) params.get("processId");
        String content = (String) params.get("content");
        Task task = this.taskService.createTaskQuery().processInstanceId(processId).singleResult();
        taskService.addComment(task.getId(), processId, content);
        taskService.complete(task.getId());
    }
}
