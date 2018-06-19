/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.renren.modules.sys.controller;

import io.renren.common.component.DictComponent;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.entity.SysDictEntity;
import io.renren.modules.sys.service.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 数据字典
 *
 * @author Mark sunlightcs@gmail.com
 * @since 3.1.0 2018-01-27
 */
@RestController
@RequestMapping("sys/dict")
@Api(description="数据字典")
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;

    /**
     * 列表
     */
    @RequestMapping(value="/list", method=RequestMethod.GET)
    @RequiresPermissions("sys:dict:list")
    @ApiOperation(value="数据字典列表", notes="返回数据字典列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysDictService.queryPage(params);
        // 画面数据字典类型下拉菜单
        List<SysDictEntity> typeList = sysDictService.getSysDictEntityGroupByType();
        // 结果map
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("userdata", typeList);
        return R.ok(map);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info/{id}", method=RequestMethod.GET)
    @RequiresPermissions("sys:dict:info")
    @ApiOperation(value="查询数据字典", notes="通过id查询数据字典")
    public R info(@ApiParam(required=true) @PathVariable("id") Long id){
        SysDictEntity dict = sysDictService.selectById(id);

        return R.ok().put("dict", dict);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save", method=RequestMethod.POST)
    @RequiresPermissions("sys:dict:save")
    @ApiOperation(value="增加", notes="增加数据字典")
    public R save(@RequestBody SysDictEntity dict){
        //校验类型
        ValidatorUtils.validateEntity(dict);
        sysDictService.insertDict(dict);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update", method=RequestMethod.POST)
    @RequiresPermissions("sys:dict:update")
    @ApiOperation(value="修改", notes="修改数据字典")
    public R update(@RequestBody SysDictEntity dict){
        //校验类型
        ValidatorUtils.validateEntity(dict);
        sysDictService.updateDict(dict);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    @RequiresPermissions("sys:dict:delete")
    @ApiOperation(value="删除", notes="删除数据字典")
    public R delete(@ApiParam(required=true) @RequestBody Long[] ids){
    	sysDictService.deleteDict(ids);
        return R.ok();
    }

//    /**
//     * 传递多个type类型返回画面所需要的数据字典
//     * @param types
//     * @return
//     */
//    @RequestMapping(value="/dictCache/{types}", method=RequestMethod.GET)
//    @ApiOperation(value="取得页面所需数据字典", notes="传递多个type类型返回画面所需要的数据字典")
//    public R dictList(@PathVariable("types") String types) {
//    	Map<String, Object> resultMap = DictComponent.getDictCacheDataByTypes(types);
//    	return R.ok(resultMap);
//    }
    
}