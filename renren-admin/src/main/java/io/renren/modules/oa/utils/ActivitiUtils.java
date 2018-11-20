package io.renren.modules.oa.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  activiti工具类
 *
 * @author zhoujy
 * @email
 * @date 2018-11-19 10:18:19
 */
@Component
public class ActivitiUtils {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;


    /**
     * 通过流程实例id取得流程实例
     * @param processInstanceId
     * @return
     */
    public ProcessInstance getProcessInstanceById(String processInstanceId) {
        return runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    }

    /**
     * 通过taskId取得流程实例
     * @param taskId
     * @return
     */
    public ProcessInstance getProcessInstanceByTaskId(String taskId) {
        Task task = getTaskById(taskId);
        return getProcessInstanceById(task.getProcessInstanceId());
    }

    /**
     * 通过流程实例id取得任务
     * @param processInstanceId
     * @return
     */
    public Task getTaskById(String processInstanceId) {
        return taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
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

}
