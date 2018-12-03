package io.renren.modules.oa.component;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OaRuntimeService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    /**
     * 启动流程并完成一个任务
     * @param key 流程key
     * @param var1
     * @param var2
     */
    public ProcessInstance startProcessInstanceAndCompleteTask(String key, Map<String, Object> var1, Map<String, Object> var2) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, var1);
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        taskService.complete(task.getId(), var2);
        return processInstance;
    }

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
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        return getProcessInstanceById(task.getProcessInstanceId());
    }

    public RuntimeService getRuntimeService() {
        return runtimeService;
    }
}
