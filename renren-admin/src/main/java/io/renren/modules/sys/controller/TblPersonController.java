package io.renren.modules.sys.controller;

import java.util.Arrays;
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

import io.renren.modules.sys.entity.TblPersonEntity;
import io.renren.modules.sys.service.TblPersonService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-26 20:42:54
 */
@RestController
@RequestMapping("sys/tblperson")
public class TblPersonController extends AbstractController{
    @Autowired
    private TblPersonService tblPersonService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:tblperson:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tblPersonService.queryPage(params);
        // test 
        List<TblPersonEntity> listPerson = this.tblPersonService.queryPersonList();
        return R.ok().put("page", page);
    }
    
    /**
     * 信息
     */
    @RequestMapping("/info/{personId}")
    @RequiresPermissions("sys:tblperson:info")
    public R info(@PathVariable("personId") Integer personId){
        TblPersonEntity tblPerson = tblPersonService.selectById(personId);
        
        return R.ok().put("tblPerson", tblPerson);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:tblperson:save")
    public R save(@RequestBody TblPersonEntity tblPerson){
    	ValidatorUtils.validateEntity(tblPerson);
        tblPersonService.insert(tblPerson);
        
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:tblperson:update")
    public R update(@RequestBody TblPersonEntity tblPerson){
        ValidatorUtils.validateEntity(tblPerson);
        tblPersonService.updateAllColumnById(tblPerson);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:tblperson:delete")
    public R delete(@RequestBody Integer[] personIds){
        tblPersonService.deleteBatchIds(Arrays.asList(personIds));

        return R.ok();
    }

}
