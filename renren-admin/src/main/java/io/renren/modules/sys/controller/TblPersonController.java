package io.renren.modules.sys.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import io.renren.dictionary.component.DictComponent;
import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.sys.entity.TblPersonEntity;
import io.renren.modules.sys.service.TblPersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.springframework.web.multipart.MultipartFile;


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
        return R.ok(map);
    }
    
    /**
     * 信息
     */
    @RequestMapping(value="/info/{personId}", method=RequestMethod.GET)
    @RequiresPermissions("sys:tblperson:info")
    @ApiOperation(value="查询人员", notes="通过id查询人员")
    public R info(@PathVariable("personId") Integer personId){
        TblPersonEntity tblPerson = tblPersonService.getById(personId);
        
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
        tblPersonService.save(tblPerson);
        
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
        tblPersonService.updateById(tblPerson);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    @RequiresPermissions("sys:tblperson:delete")
    @ApiOperation(value="删除", notes="删除人员")
    public R delete(@RequestBody Integer[] personIds){
        tblPersonService.removeByIds(Arrays.asList(personIds));

        return R.ok();
    }

    @RequiresPermissions("sys:tblperson:import")
    @RequestMapping("/importUsers")
    public R importUsers(@RequestParam("excel_users") MultipartFile file) {
//        // 获取文件名
//        String fileName = file.getOriginalFilename();
//        logger.info("上传的文件名为：" + fileName);
//        // 获取文件的后缀名
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));
//        logger.info("上传的后缀名为：" + suffixName);
//        // 文件上传后的路径
//        String filePath = "E://UsersExcels//";
//        // 解决中文问题，liunx下中文路径，图片显示问题
//        // fileName = UUID.randomUUID() + suffixName;
//        File dest = new File(filePath + fileName);
//        // 检测是否存在目录
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdirs();
//        }
//        try {
//            file.transferTo(dest);
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        tblPersonService.importUsers(file);
        return R.ok("人员导入成功！");
    }






    public static final String ROOT = "upload-dir";

    private final ResourceLoader resourceLoader;

    @Autowired
    public TblPersonController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    @RequestMapping("/uploadUserImage")
    public R uploadUserImage(@RequestParam("img_user") MultipartFile file) {

        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = "E:/UsersImages/";
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return R.ok().put("headImg", fileName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/showImage/{filename}")
    public ResponseEntity<?> showImage(@PathVariable("filename") String filename) {

        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + "E:/UsersImages/" + filename));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
