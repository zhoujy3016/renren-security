package io.renren.modules.oa.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

public interface ITaskService {

    TaskService getTaskService();

    Task getTask(String processInstanceId);

    Task getTask(String processInstanceId, String userId);

    Page<Task> getTaskListPageByAssigneeId(String assigneeId, String page, String limit);

    void completeTaskWithComment(String processInstanceId, String comment, Map<String, Object> var);

    List<Task> getTaskListByAssigneeId(String assigneeId);

    int getTaskListCountByAssigneeId(String assigneeId);
}
