package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.BteQuestionEntity;
import io.renren.modules.sys.service.BteQuestionService;
import io.renren.common.component.DictComponent;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-19 15:11:26
 */
@RestController
@RequestMapping("sys/btequestion")
public class BteQuestionController {
    @Autowired
    private BteQuestionService bteQuestionService;

    @Autowired
    private DictComponent dictComponent;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:btequestion:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bteQuestionService.queryPage(params);
        Map<String, Object> map = new HashMap<>();
    	map.put("page", page);
    	map.put("userdata", dictComponent.getDictCacheDataByTypes("stlx"));
    	return R.ok(map);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{dataNo}")
    @RequiresPermissions("sys:btequestion:info")
    public R info(@PathVariable("dataNo") Integer dataNo){
        BteQuestionEntity bteQuestion = bteQuestionService.selectById(dataNo);

        return R.ok().put("bteQuestion", bteQuestion);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:btequestion:save")
    public R save(@RequestBody BteQuestionEntity bteQuestion){
    	// 校验
    	ValidatorUtils.validateEntity(bteQuestion);
        bteQuestionService.insertQuestion(bteQuestion);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:btequestion:update")
    public R update(@RequestBody BteQuestionEntity bteQuestion){
    	// 校验
        ValidatorUtils.validateEntity(bteQuestion);
        bteQuestionService.updateAllColumnById(bteQuestion);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:btequestion:delete")
    public R delete(@RequestBody Integer[] dataNos){
        bteQuestionService.deleteBatchIds(Arrays.asList(dataNos));

        return R.ok();
    }
    
    @RequestMapping("/state")
    @RequiresPermissions("sys:btequestion:state")
    public R state(@RequestBody Integer[] dataNos) {
    	bteQuestionService.changeQuestionStage(dataNos);
    	
    	return R.ok();
    }
    

}
