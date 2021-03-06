package io.renren.modules.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.annotation.DataCreatorFilter;
import io.renren.common.utils.*;

import io.renren.modules.oa.component.OaHistoryService;
import io.renren.modules.oa.component.OaRuntimeService;
import io.renren.modules.oa.component.OaTaskService;
import io.renren.modules.oa.entity.ProcessEntity;
import io.renren.modules.oa.entity.TaskEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
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
    private OaRuntimeService iRuntimeService;

    @Autowired
    private OaTaskService iTaskService;

    @Autowired
    private OaHistoryService iHistoryService;

    @Override
    @DataCreatorFilter(tableAlias = "oa_vacation", userId = "user_id")
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OaVacationEntity> page = (Page<OaVacationEntity>) this.baseMapper.selectPage(
                new Query<OaVacationEntity>(params).getPage(),
                new QueryWrapper<OaVacationEntity>().apply(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );

        List<ProcessEntity> processEntityList = new ArrayList<>();

        for(OaVacationEntity oaVacationEntity:page.getRecords()) {
            String processId = oaVacationEntity.getProcessId();
            ProcessInstance pi = iRuntimeService.getProcessInstanceById(processId);
            ProcessEntity processEntity = null;
            if(pi == null) {
                processEntity = new ProcessEntity();
                processEntity.setTitle("完结");
            } else {
                processEntity = (ProcessEntity) iRuntimeService.getRuntimeService().getVariable(pi.getId(),"pro");
            }
            processEntityList.add(processEntity);
        }

        Page<ProcessEntity> entityPage = new Page(page.getCurrent(), page.getSize(), page.getTotal());
        entityPage.setRecords(processEntityList);
        return new PageUtils(entityPage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void startVacation(OaVacationEntity oaVacationEntity, SysUserEntity user) {
        Map<String, Object> var1 = new HashMap<>();
        var1.put("userId", user.getUserId());
//        var1.put("title", user.getUsername() + "请假申请");
//        var1.put("datetime", J8DateUtils.format(LocalDateTime.now(), J8DateUtils.DATE_TIME_PATTERN));
        ProcessEntity processEntity = new ProcessEntity();
        processEntity.setTitle(user.getUsername() + "请假申请");
        processEntity.setCreateDateTime(LocalDateTime.now());
        var1.put("pro", processEntity);

        Map<String, Object> var2 = new HashMap<>();
        // 指定受理人
        var2.put("managerId", "1");
        var2.put("days", oaVacationEntity.getVaDays());

        ProcessInstance processInstance = iRuntimeService.startProcessInstanceAndCompleteTask("VacationProcess", var1, var2);

        oaVacationEntity.setUserId(user.getUserId());
        oaVacationEntity.setProcessId(processInstance.getId());
        this.save(oaVacationEntity);
    }

    @Override
    public List<Comment> queryCommentInfo(OaVacationEntity oaVacationEntity) {
        String processId = oaVacationEntity.getProcessId();
        return iHistoryService.getCommentListByProcessInstanceId(processId);
    }

    @Override
    public void updateVacation(OaVacationEntity oaVacationEntity, SysUserEntity user) {
        this.updateById(oaVacationEntity);
        Map<String, Object> var = new HashMap<>();
        var.put("managerId", "1");
        var.put("days", oaVacationEntity.getVaDays());
        Task task = iTaskService.getTask(oaVacationEntity.getProcessId(), String.valueOf(user.getUserId()));
        iTaskService.getTaskService().complete(task.getId(), var);
    }

    @Override
    public PageUtils queryTaskPage(Map<String, Object> params, SysUserEntity userEntity) {
        Page<Task> page = iTaskService.getTaskListPageByAssigneeId(String.valueOf(userEntity.getUserId()),
                String.valueOf(params.get("page")),
                String.valueOf(params.get("limit")));
        List<TaskEntity> result = new ArrayList<>(page.getRecords().size());
        for(Task task: page.getRecords()) {
            ProcessInstance pi = iRuntimeService.getProcessInstanceByTaskId(task.getId());
            ProcessEntity processEntity = (ProcessEntity) iRuntimeService.getRuntimeService().getVariable(pi.getId(), "pro");
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setProcessId(pi.getId());
            taskEntity.setTitle(processEntity.getTitle());
            taskEntity.setRequestDate(J8DateUtils.format(processEntity.getCreateDateTime(), J8DateUtils.DATE_TIME_PATTERN));
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
        Boolean isAgree = (Boolean) params.get("isAgree");
        Map<String, Object> var = new HashMap<>();
        var.put("agree", isAgree);
        iTaskService.completeTaskWithComment(processId, content, var);
    }
}
