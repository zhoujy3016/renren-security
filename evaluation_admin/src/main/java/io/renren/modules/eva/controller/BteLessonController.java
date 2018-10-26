package io.renren.modules.eva.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.eva.entity.BteLessonEntity;
import io.renren.modules.eva.service.BteLessonService;
import io.renren.dictionary.component.DictComponent;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-20 09:55:48
 */
@RestController
@RequestMapping("eva/btelesson")
public class BteLessonController {
    @Autowired
    private BteLessonService bteLessonService;

    @Autowired
    private DictComponent dictComponent;

    /**
     * 列表
     */
    @RequestMapping("/list/{evalId}")
    public R list(@PathVariable("evalId") Integer evalId, @RequestParam Map<String, Object> params){
    	// 通过测评evalId 将该测评下的课程查询
        PageUtils page = bteLessonService.queryPage(params, evalId);
        Map<String, Object> map = new HashMap<>();
    	map.put("page", page);
    	// 数据字典
    	map.put("userdata", dictComponent.getDictCacheDataByTypes("gglx, zylx"));
    	// 测评编号
    	map.put("evalId", evalId);
    	return R.ok(map);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{dataNo}")
    public R info(@PathVariable("dataNo") Integer dataNo){
        BteLessonEntity bteLesson = bteLessonService.selectById(dataNo);
        return R.ok().put("bteLesson", bteLesson);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BteLessonEntity bteLesson){
    	ValidatorUtils.validateEntity(bteLesson);
        bteLessonService.insert(bteLesson);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BteLessonEntity bteLesson){
        ValidatorUtils.validateEntity(bteLesson);
        bteLessonService.updateAllColumnById(bteLesson);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] dataNos){
        bteLessonService.deleteBatchIds(Arrays.asList(dataNos));

        return R.ok();
    }

}
