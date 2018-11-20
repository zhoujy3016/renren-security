package io.renren.modules.oa.utils;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *  activiti工具类
 *
 * @author zhoujy
 * @email
 * @date 2018-11-19 10:18:19
 */
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
     *
     * @param assigneeId
     * @param start 数据开始的索引
     * @param size 结果的数量
     * @return
     */
    public List<Task> getTaskListPageByAssigneeId(String assigneeId, int start, int size) {
        return taskService.createTaskQuery().taskAssignee(assigneeId).listPage(start, size);
    }

}
