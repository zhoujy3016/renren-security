package io.renren.modules.oa.utils;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

public class ActivitiUtils {
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    public ProcessInstance getProcessInstance(String processInstanceId) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        return processInstance;
    }

    public Task getTask(String processInstanceId) {
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        return task;
    }


}
