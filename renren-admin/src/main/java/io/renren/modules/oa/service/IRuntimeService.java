package io.renren.modules.oa.service;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.Map;

public interface IRuntimeService {

    ProcessInstance startProcessInstanceAndCompleteTask(String key, Map<String, Object> var1, Map<String, Object> var2);

    ProcessInstance getProcessInstanceById(String processInstanceId);

    ProcessInstance getProcessInstanceByTaskId(String taskId);

    RuntimeService getRuntimeService();
}
