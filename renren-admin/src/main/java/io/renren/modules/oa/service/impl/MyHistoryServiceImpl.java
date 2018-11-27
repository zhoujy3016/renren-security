package io.renren.modules.oa.service.impl;

import io.renren.modules.oa.service.IHistoryService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyHistoryServiceImpl implements IHistoryService {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private TaskService taskService;

    @Override
    public HistoryService getHistoryService() {
        return historyService;
    }

    @Override
    public HistoricProcessInstance getHistoricProcessInstanceById(String processInstanceId) {
        return historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    }

    @Override
    public List<Comment> getCommentListByProcessInstanceId(String processInstanceId) {
        List<Comment> commentList = new ArrayList<>();
        List<HistoricActivityInstance> his = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
        for(HistoricActivityInstance hai:his) {
            String hTaskId = hai.getTaskId();
            List<Comment> comments = taskService.getTaskComments(hTaskId);
            commentList.addAll(comments);
        }
        return commentList;
    }


}
