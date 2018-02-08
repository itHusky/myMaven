package com.zyh.controller.mainCode;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zyh.controller.base.BaseController;
import com.zyh.service.mainCode.IRolesService;
import com.zyh.vo.base.JsonResult;

/**
 * 角色资源模块管理层
 *
 * @author      1101399
 * @CreateDate  2018-2-5 下午2:53:04
 */
@Controller
@RequestMapping("/roles")
public class RolesController extends BaseController{

    @Resource
    private IRolesService rolesService;

    @RequestMapping("/list")
    public String list(ModelMap model){
        rolesService.findAll();
        return "";
    }

    @RequestMapping("/show")
    public String show(ModelMap model){
        return "";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam Integer id,ModelMap model){
        rolesService.findById(id);
        return "";
    }

    @RequestMapping("/save")
    public JsonResult<String> save(){
        return new JsonResult<String>(true,"保存成功！");
    }

    @RequestMapping("/delete")
    public JsonResult<String> delete(@RequestParam Integer id){
        rolesService.deleteById(id);
        return new JsonResult<String>(true,"删除成功！");
    }
}
