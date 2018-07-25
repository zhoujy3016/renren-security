package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.renren.common.component.DictComponent;
import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.BteLessonTypeEntity;
import io.renren.modules.sys.service.BteLessonTypeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-07-02 09:44:42
 */
@RestController
@RequestMapping("sys/btelessontype")
public class BteLessonTypeController {
    @Autowired
    private BteLessonTypeService bteLessonTypeService;

    @Autowired
    private DictComponent dictComponent;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:btelessontype:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bteLessonTypeService.queryPage(params);
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        return R.ok(map);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{dataNo}")
    @RequiresPermissions("sys:btelessontype:info")
    public R info(@PathVariable("dataNo") Integer dataNo){
        BteLessonTypeEntity bteLessonType = bteLessonTypeService.selectById(dataNo);

        return R.ok().put("bteLessonType", bteLessonType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:btelessontype:save")
    public R save(@RequestBody BteLessonTypeEntity bteLessonType){
        ValidatorUtils.validateEntity(bteLessonType);
        bteLessonTypeService.insertLessonType(bteLessonType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:btelessontype:update")
    public R update(@RequestBody BteLessonTypeEntity bteLessonType){
        ValidatorUtils.validateEntity(bteLessonType);
        bteLessonTypeService.updateLessonType(bteLessonType);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:btelessontype:delete")
    public R delete(@RequestBody Integer[] dataNos){
        bteLessonTypeService.deleteLessonType(dataNos);

        return R.ok();
    }

}
