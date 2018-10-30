package io.renren.modules.eva.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.eva.entity.BteResultEntity;
import io.renren.modules.eva.entity.BteResultEntityExt;
import io.renren.modules.eva.service.BteResultService;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-20 14:30:53
 */
@RestController
@RequestMapping("eva/bteresult")
public class BteResultController extends AbstractController {
    @Autowired
    private BteResultService bteResultService;

    /**
     * 列表
     */
    @RequestMapping("/list/{evalId}")
    public R list(@PathVariable("evalId") Integer evalId, @RequestParam Map<String, Object> params){
    	// 通过测评evalId 将该测评下的课程查询
    	List<BteResultEntityExt> resultList = bteResultService.queryResultList(evalId);
    	Map<String, Object> map = new HashMap<>();
    	map.put("result", resultList);
    	map.put("evalId", evalId);
        return R.ok(map);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{dataNo}")
    public R info(@PathVariable("dataNo") Integer dataNo){
        BteResultEntity bteResult = bteResultService.getById(dataNo);

        return R.ok().put("bteResult", bteResult);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BteResultEntity bteResult){
        bteResultService.save(bteResult);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BteResultEntity bteResult){
        ValidatorUtils.validateEntity(bteResult);
        //全部更新
        bteResultService.updateById(bteResult);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] dataNos){
        bteResultService.removeByIds(Arrays.asList(dataNos));

        return R.ok();
    }
    
    /**
     * 导出测评结果
     * @param evalId
     * @return
     */
    @RequestMapping("/exportResult/{evalId}")
    public void exportResult(@PathVariable("evalId") Integer evalId, HttpServletResponse httpServletResponse) {
    	// 导出测评结果
    	this.bteResultService.exportResult(evalId, httpServletResponse);
    }
    
    /**
     * 导出具体建议
     * @param evalId
     * @param httpServletResponse
     */
    @RequestMapping("/exportSuggest/{evalId}")
    public void exportSuggest(@PathVariable("evalId") Integer evalId, HttpServletResponse httpServletResponse) {
    	this.bteResultService.exportSuggest(evalId, httpServletResponse);
    }
    
}
