package io.renren.modules.oa.component;

import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OaHistoryService {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private TaskService taskService;

    public HistoryService getHistoryService() {
        return historyService;
    }


    public HistoricProcessInstance getHistoricProcessInstanceById(String processInstanceId) {
        return historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    }

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
