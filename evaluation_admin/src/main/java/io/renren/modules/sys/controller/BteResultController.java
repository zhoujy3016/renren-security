package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.BteResultEntity;
import io.renren.modules.sys.service.BteResultService;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-20 14:30:53
 */
@RestController
@RequestMapping("sys/bteresult")
public class BteResultController {
    @Autowired
    private BteResultService bteResultService;

    /**
     * 列表
     */
    @RequestMapping("/list/{evalId}")
    @RequiresPermissions("sys:bteresult:list")
    public R list(@PathVariable("evalId") Integer evalId, @RequestParam Map<String, Object> params){
    	// 通过测评evalId 将该测评下的课程查询
//    	PageUtils page = bteResultService.queryPage(params);
    	List<BteResultEntity> resultList = bteResultService.queryResultList(evalId);
    	Map<String, Object> map = new HashMap<>();
    	map.put("result", resultList);
    	map.put("evalId", evalId);
        return R.ok(map);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{dataNo}")
    @RequiresPermissions("sys:bteresult:info")
    public R info(@PathVariable("dataNo") Integer dataNo){
        BteResultEntity bteResult = bteResultService.selectById(dataNo);

        return R.ok().put("bteResult", bteResult);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:bteresult:save")
    public R save(@RequestBody BteResultEntity bteResult){
        bteResultService.insert(bteResult);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:bteresult:update")
    public R update(@RequestBody BteResultEntity bteResult){
        ValidatorUtils.validateEntity(bteResult);
        bteResultService.updateAllColumnById(bteResult);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:bteresult:delete")
    public R delete(@RequestBody Integer[] dataNos){
        bteResultService.deleteBatchIds(Arrays.asList(dataNos));

        return R.ok();
    }

}
