package com.zyh.controller.mainCode;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zyh.controller.base.BaseController;
import com.zyh.service.mainCode.IRolePermissionService;
import com.zyh.vo.base.JsonResult;

/**
 * 角色权限对应模块管理
 *
 * @author      1101399
 * @CreateDate  2018-2-5 下午3:03:17
 */
@Controller
@RequestMapping("/rolePermission")// RolePermission
public class RolePermissionController extends BaseController{

    @Resource
    private IRolePermissionService rolePermissionService;

    @RequestMapping("/list")
    public String list(ModelMap model){
        rolePermissionService.findAll();
        return "";
    }

    @RequestMapping("/show")
    public String show(@RequestParam Integer id){
        rolePermissionService.findById(id);
        return "";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam Integer id){
        rolePermissionService.findById(id);
        return "";
    }

    @RequestMapping("/save")
    public JsonResult<String> save(){
        return new JsonResult<String>(true,"保存成功！");
    }

    @RequestMapping("/detele")
    public JsonResult<String> detele(@RequestParam Integer id){
        return new JsonResult<String>(true,"删除成功！");
    }
}
