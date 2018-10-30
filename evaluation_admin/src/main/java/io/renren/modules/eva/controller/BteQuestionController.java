package io.renren.modules.eva.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.eva.entity.BteQuestionEntity;
import io.renren.modules.eva.service.BteQuestionService;
import io.renren.dictionary.component.DictComponent;
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
@RequestMapping("eva/btequestion")
public class BteQuestionController extends AbstractController {
    @Autowired
    private BteQuestionService bteQuestionService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("eva:btequestion:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bteQuestionService.queryPage(params);
        Map<String, Object> map = new HashMap<>();
    	map.put("page", page);
    	return R.ok(map);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{dataNo}")
    @RequiresPermissions("eva:btequestion:info")
    public R info(@PathVariable("dataNo") Integer dataNo){
        BteQuestionEntity bteQuestion = bteQuestionService.getById(dataNo);

        return R.ok().put("bteQuestion", bteQuestion);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("eva:btequestion:save")
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
    @RequiresPermissions("eva:btequestion:update")
    public R update(@RequestBody BteQuestionEntity bteQuestion){
    	// 校验
        ValidatorUtils.validateEntity(bteQuestion);
        //全部更新
        bteQuestionService.updateById(bteQuestion);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("eva:btequestion:delete")
    public R delete(@RequestBody Integer[] dataNos){
        bteQuestionService.removeByIds(Arrays.asList(dataNos));

        return R.ok();
    }
    
    @RequestMapping("/state")
    @RequiresPermissions("eva:btequestion:state")
    public R state(@RequestBody Integer[] dataNos) {
    	bteQuestionService.changeQuestionStage(dataNos);
    	
    	return R.ok();
    }
    

}
