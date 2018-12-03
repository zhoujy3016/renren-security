package io.renren.modules.oa.component;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class OaTaskService {

    @Autowired
    private TaskService taskService;

    /**
     * 通过流程实例id取得任务
     * @param processInstanceId
     * @return
     */
    public Task getTask(String processInstanceId) {
        return taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    }

    /**
     *
     * @param processInstanceId
     * @param userId
     * @return
     */
    public Task getTask(String processInstanceId, String userId) {
        return taskService.createTaskQuery().processInstanceId(processInstanceId).taskAssignee(userId).singleResult();
    }


    /**
     * 完成一个任务并提交一个comment
     * @param processInstanceId
     * @param comment
     * @param var
     */
    public void completeTaskWithComment(String processInstanceId, String comment, Map<String, Object> var) {
        Task task = getTask(processInstanceId);
        taskService.addComment(task.getId(), processInstanceId, comment);
        taskService.complete(task.getId(), var);
    }

    /**
     * 取得任务列表
     * @param assigneeId
     * @return
     */
    public List<Task> getTaskListByAssigneeId(String assigneeId) {
        return taskService.createTaskQuery().taskAssignee(assigneeId).list();
    }

    /**
     *  根据分页数据与处理人id取得分页数据
     * @param assigneeId
     * @param page 页数
     * @param limit 每页数量
     * @return
     */
    public Page<Task> getTaskListPageByAssigneeId(String assigneeId, String page, String limit) {
        int current = Integer.parseInt(page);
        int size = Integer.parseInt(limit);
        int total = getTaskListCountByAssigneeId(assigneeId);
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(assigneeId).listPage((current - 1) * size, size);
        Page<Task> pagePlugin = new Page(current, size, total);
        pagePlugin.setRecords(taskList);
        return pagePlugin;
    }

    /**
     * 取得该处理人所有任务的数
     * @param assigneeId
     * @return
     */
    public int getTaskListCountByAssigneeId(String assigneeId) {
        return (int)taskService.createTaskQuery().taskAssignee(assigneeId).count();
    }

    public TaskService getTaskService() {
        return taskService;
    }

}
