package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.BteEvaluateEntity;
import io.renren.modules.sys.service.BteEvaluateService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.QrcodeUtil;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-19 21:00:48
 */
@RestController
@RequestMapping("sys/bteevaluate")
public class BteEvaluateController {
    @Autowired
    private BteEvaluateService bteEvaluateService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:bteevaluate:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bteEvaluateService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{dataNo}")
    @RequiresPermissions("sys:bteevaluate:info")
    public R info(@PathVariable("dataNo") Integer dataNo){
        BteEvaluateEntity bteEvaluate = bteEvaluateService.selectById(dataNo);

        return R.ok().put("bteEvaluate", bteEvaluate);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:bteevaluate:save")
    public R save(@RequestBody BteEvaluateEntity bteEvaluate){
    	ValidatorUtils.validateEntity(bteEvaluate);
        bteEvaluateService.insertEvaluate(bteEvaluate);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:bteevaluate:update")
    public R update(@RequestBody BteEvaluateEntity bteEvaluate){
        ValidatorUtils.validateEntity(bteEvaluate);
        bteEvaluateService.updateAllColumnById(bteEvaluate);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:bteevaluate:delete")
    public R delete(@RequestBody Integer[] dataNos){
        bteEvaluateService.deleteBatchIds(Arrays.asList(dataNos));

        return R.ok();
    }
    
    @RequestMapping("/downloadQr/{dataNo}")
    public R downloadQr(@PathVariable("dataNo") Integer dataNo, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
    	// 生成二维码
    	String base64 = this.bteEvaluateService.buildQrCode(dataNo, httpServletRequest, httpServletResponse);
    	return R.ok().put("qrCode", base64);
    }
    
    /**
     * 选中数据变为未开始状态
     * @param toState
     * @param dataNos
     * @return
     */
    @RequestMapping("/nostart/{toState}")
    @RequiresPermissions("sys:bteevaluate:nostart")
    public R noStart(@PathVariable("toState") Integer toState, @RequestBody Integer[] dataNos) {
    	bteEvaluateService.changeEvalStage(toState, dataNos);
    	return R.ok();
    }

    /**
     * 选中数据变为开始状态
     * @param toState
     * @param dataNos
     * @return
     */
    @RequestMapping("/start/{toState}")
    @RequiresPermissions("sys:bteevaluate:start")
    public R starting(@PathVariable("toState") Integer toState, @RequestBody Integer[] dataNos) {
    	bteEvaluateService.changeEvalStage(toState, dataNos);
    	return R.ok();
    }
    
    /**
     * 选中数据变为结束状态
     * @param toState
     * @param dataNos
     * @return
     */
    @RequestMapping("/over/{toState}")
    @RequiresPermissions("sys:bteevaluate:over")
    public R over(@PathVariable("toState") Integer toState, @RequestBody Integer[] dataNos) {
    	bteEvaluateService.changeEvalStage(toState, dataNos);
    	return R.ok();
    }
}
