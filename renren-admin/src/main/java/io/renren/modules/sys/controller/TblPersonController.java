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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.TblPersonEntity;
import io.renren.modules.sys.service.TblPersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(description="报名人员")
public class TblPersonController extends AbstractController{
    @Autowired
    private TblPersonService tblPersonService;

    @Autowired
    private DictComponent dictComponent;

    /**
     * 列表
     */
    @RequestMapping(value="/list", method=RequestMethod.GET)
    @RequiresPermissions("sys:tblperson:list")
    @ApiOperation(value="人员列表", notes="返回人员列表")
    public R list(@RequestParam Map<String, Object> params){
        
    	// PageUtils page = tblPersonService.queryPage(params);
        // test 
        //List<TblPersonEntity> listPerson = this.tblPersonService.queryPersonList();
        
    	// 自定义分页查询
    	PageUtils page = tblPersonService.selectPersonList(params);
    	
    	Map<String, Object> map = new HashMap<>();
    	map.put("page", page);
    	map.put("userdata", dictComponent.getDictCacheDataByTypes("mz,area"));
        return R.ok(map);
    }
    
    /**
     * 信息
     */
    @RequestMapping(value="/info/{personId}", method=RequestMethod.GET)
    @RequiresPermissions("sys:tblperson:info")
    @ApiOperation(value="查询人员", notes="通过id查询人员")
    public R info(@PathVariable("personId") Integer personId){
        TblPersonEntity tblPerson = tblPersonService.selectById(personId);
        
        return R.ok().put("tblPerson", tblPerson);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save", method=RequestMethod.POST)
    @RequiresPermissions("sys:tblperson:save")
    @ApiOperation(value="增加", notes="增加人员")
    public R save(@RequestBody TblPersonEntity tblPerson){
    	ValidatorUtils.validateEntity(tblPerson);
        tblPersonService.insert(tblPerson);
        
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update", method=RequestMethod.POST)
    @RequiresPermissions("sys:tblperson:update")
    @ApiOperation(value="修改", notes="修改人员")
    public R update(@RequestBody TblPersonEntity tblPerson){
        ValidatorUtils.validateEntity(tblPerson);
        tblPersonService.updateAllColumnById(tblPerson);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    @RequiresPermissions("sys:tblperson:delete")
    @ApiOperation(value="删除", notes="删除人员")
    public R delete(@RequestBody Integer[] personIds){
        tblPersonService.deleteBatchIds(Arrays.asList(personIds));

        return R.ok();
    }

}
