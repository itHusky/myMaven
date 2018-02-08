package com.zyh.controller.mainCode;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zyh.controller.base.BaseController;
import com.zyh.domain.mainCode.PermissionsResource;
import com.zyh.service.mainCode.IPermissionsResourceService;
import com.zyh.vo.base.JsonResult;

/**
 * 权限资源管理模块
 *
 * @author 1101399
 * @CreateDate 2018-2-2 上午9:38:05
 */
@Controller
@RequestMapping("/permissionsResource")
public class PermissionsResourceController extends BaseController {

    @Resource
    private IPermissionsResourceService permissionsResourceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public String list(ModelMap model) {
        List<PermissionsResource> temPerRes = permissionsResourceService.findAll();
        model.put("PermissionsResource", temPerRes);
        return "";
    }

    /**
     * 展示
     */
    @RequestMapping("/show")
    public String show(@RequestParam Integer id, ModelMap model) {
        PermissionsResource temPerRes = permissionsResourceService.findById(id);
        model.put("PermissionsResource", temPerRes);
        return "";
    }

    /**
     * 编辑
     */
    @RequestMapping("/edit")
    public String edit(@RequestParam Integer id,ModelMap model){
        /*
         * 封装前台传递类 资源类
         *
         * 也可以添加URL信息等
         *
         * */
        PermissionsResource perRes = permissionsResourceService.findById(id);
        model.put("PerRes", perRes);
        return "";
    }

    @RequestMapping("/save")
    public JsonResult<String> save(){
        return new JsonResult<String>(true,"保存成功！");
    }

    /**
     * 删除
     **/
    @RequestMapping("/delete")
    public JsonResult<String> delete(@RequestParam Integer id){
        permissionsResourceService.deleteById(id);
        return new JsonResult<String>(true,"删除成功");
    }

    /**
     * 缺省配置列表
     */
    @RequestMapping("/defaultList")
    public String defaultList(ModelMap model){
        /*
         * 缺省:查询出没有配置过的URL
         * */
        return "";
    }


}
