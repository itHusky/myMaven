package com.zyh.controller.mainCode;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zyh.controller.base.BaseController;
import com.zyh.domain.mainCode.Resources;
import com.zyh.service.mainCode.IResourcesService;
import com.zyh.vo.base.JsonResult;

/**
 * URL资源模块管理层
 *
 * @author      1101399
 * @CreateDate  2018-2-5 下午2:54:02
 */
@Controller
@RequestMapping("/resources")
public class ResourcesController extends BaseController{

    @Resource
    private IResourcesService resourcesService;

    @RequestMapping("/list")
    public String list(ModelMap model){
        List<Resources> res = resourcesService.findAll();
        model.put("resources", res);
        return "";
    }

    @RequestMapping("/show")
    public String show(){
        return "";
    }

    @RequestMapping("/edit")
    public String edit(){
        return "";
    }

    @RequestMapping("/save")
    public JsonResult<String> save(){
        return new JsonResult<String>(true,"保存成功！");
    }

    @RequestMapping("/delete")
    public JsonResult<String> delete(@RequestParam Integer id){
        resourcesService.deleteById(id);
        return new JsonResult<String>(true,"删除成功！");
    }
}
