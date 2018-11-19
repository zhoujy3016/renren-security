package io.renren.modules.oa.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.oa.service.OaVacationService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * class:
 * description：
 * author；zhoujy
 * date:2018/11/19
 */
@RestController
@RequestMapping("oa/oavacationea")
public class OaVacationEaController extends AbstractController {

    @Autowired
    private OaVacationService oaVacationService;
    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("oa:oavacationea:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = oaVacationService.queryPage(params);

        return R.ok().put("page", page);
    }

}
