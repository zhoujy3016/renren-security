package io.renren.modules.oa.service;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Comment;

import java.util.List;

public interface IHistoryService {
    HistoryService getHistoryService();

    HistoricProcessInstance getHistoricProcessInstanceById(String processInstanceId);

    List<Comment> getCommentListByProcessInstanceId(String processInstanceId);
}
