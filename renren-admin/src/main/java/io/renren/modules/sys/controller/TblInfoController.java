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

import io.renren.modules.sys.entity.TblInfoEntity;
import io.renren.modules.sys.service.TblInfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-27 14:01:50
 */
@RestController
@RequestMapping("sys/tblinfo")
public class TblInfoController {
    @Autowired
    private TblInfoService tblInfoService;

    @Autowired
    private DictComponent dictComponent;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:tblinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tblInfoService.queryPage(params);
        
    	Map<String, Object> map = new HashMap<>();
    	map.put("page", page);
        return R.ok(map);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{infoId}")
    @RequiresPermissions("sys:tblinfo:info")
    public R info(@PathVariable("infoId") Integer infoId){
        TblInfoEntity tblInfo = tblInfoService.selectById(infoId);

        return R.ok().put("tblInfo", tblInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:tblinfo:save")
    public R save(@RequestBody TblInfoEntity tblInfo){
    	ValidatorUtils.validateEntity(tblInfo);
    	tblInfoService.saveInfo(tblInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:tblinfo:update")
    public R update(@RequestBody TblInfoEntity tblInfo){
        ValidatorUtils.validateEntity(tblInfo);
        tblInfoService.updateAllColumnById(tblInfo);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:tblinfo:delete")
    public R delete(@RequestBody Integer[] infoIds){
        tblInfoService.deleteBatchIds(Arrays.asList(infoIds));

        return R.ok();
    }

}
