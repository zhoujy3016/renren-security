package io.renren.modules.oa.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.oa.component.OaHistoryService;
import io.renren.modules.oa.component.OaTaskService;
import io.renren.modules.sys.controller.AbstractController;
import org.activiti.engine.history.*;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.oa.entity.OaVacationEntity;
import io.renren.modules.oa.service.OaVacationService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-11-19 10:18:19
 */
@RestController
@RequestMapping("oa/oavacation")
public class OaVacationController extends AbstractController {
    @Autowired
    private OaVacationService oaVacationService;

    @Autowired
    private OaTaskService iTaskService;

    @Autowired
    private OaHistoryService iHistoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("oa:oavacation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = oaVacationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{vaId}")
    @RequiresPermissions("oa:oavacation:info")
    public R info(@PathVariable("vaId") Integer vaId){
        OaVacationEntity oaVacation = oaVacationService.getById(vaId);
        List<Comment> commentList = this.oaVacationService.queryCommentInfo(oaVacation);
        Map<String, Object> map = new HashMap<>();
        map.put("oaVacation", oaVacation);
        map.put("commentList", commentList);
        return R.ok(map);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("oa:oavacation:save")
    public R save(@RequestBody OaVacationEntity oaVacation){
        oaVacationService.startVacation(oaVacation, getUser());

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("oa:oavacation:update")
    public R update(@RequestBody OaVacationEntity oaVacation){
        ValidatorUtils.validateEntity(oaVacation);
        this.oaVacationService.updateVacation(oaVacation, getUser());
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("oa:oavacation:delete")
    public R delete(@RequestBody Integer[] vaIds){
        oaVacationService.removeByIds(Arrays.asList(vaIds));

        return R.ok();
    }

    @RequiresPermissions("oa:oavacation:approvelist")
    @RequestMapping("/approvelist")
    public R approvelist(@RequestParam Map<String, Object> params) {
        PageUtils page = oaVacationService.queryTaskPage(params, getUser());

        return R.ok().put("page", page);
    }



    @RequiresPermissions("oa:oavacation:approveinfo")
    @RequestMapping("/approveinfo/{processId}")
    public R approvelist(@PathVariable("processId") String processId) {
        OaVacationEntity oaVacation = this.oaVacationService.getOneByProcessId(processId);

        return R.ok().put("oaVacation", oaVacation);
    }

    @RequiresPermissions("oa:oavacation:approve")
    @RequestMapping("/saveApprove")
    public R saveApprove(@RequestBody Map<String, Object> params) {
        this.oaVacationService.vacationApprove(params);
        return R.ok();
    }


    @RequestMapping("/getTask/{userId}")
    public void getTaskList(@PathVariable("userId") String userId) {
        List<Task> taskList = iTaskService.getTaskService().createTaskQuery().taskAssignee(userId).list();
        taskList.stream().forEach(task -> {
            System.out.println("任务ID："+task.getId());
            System.out.println("任务的办理人："+task.getAssignee());
            System.out.println("任务名称："+task.getName());
            System.out.println("任务的创建时间："+task.getCreateTime());
            System.out.println("流程实例ID："+task.getProcessInstanceId());
            System.out.println("请假天数:" + iTaskService.getTaskService().getVariable(task.getId(), "days"));
            System.out.println("##################################");
        });

    }

    @RequestMapping("/getHistory/{userId}")
    public void getHistory(@PathVariable("userId") String userId) {
         List<HistoricProcessInstance> list =  iHistoryService.getHistoryService().createHistoricProcessInstanceQuery().list();
         for(int i = 0; i < list.size(); i++) {
             HistoricProcessInstance hpi = list.get(i);
             System.out.println("##################################");
             System.out.println("历史流程实例id:" + hpi.getId());
             List<HistoricTaskInstance> taskList = iHistoryService.getHistoryService().createHistoricTaskInstanceQuery().processInstanceId(hpi.getId()).list();

             List<Comment> commentList = iHistoryService.getCommentListByProcessInstanceId(hpi.getId());
             commentList.stream().forEach(comment -> System.out.println(comment.getFullMessage()));


             List<HistoricVariableInstance> varList = iHistoryService.getHistoryService().createHistoricVariableInstanceQuery().processInstanceId(hpi.getId()).list();


             List<HistoricActivityInstance> list1 = iHistoryService.getHistoryService().createHistoricActivityInstanceQuery().processInstanceId(hpi.getId()).taskAssignee(String.valueOf(getUserId())).list();

             for(int j = 0; j < taskList.size(); j++) {
                 HistoricTaskInstance task = taskList.get(j);
                 System.out.println("任务ID："+task.getId());
                 System.out.println("任务的办理人："+task.getAssignee());
                 System.out.println("任务名称："+task.getName());
                 System.out.println("任务的创建时间："+task.getCreateTime());
                 System.out.println("流程实例ID："+task.getProcessInstanceId());
                 System.out.println("请假天数:" + task.getProcessVariables());
                 System.out.println("--------------------------------");
             }
         }
    }

}
