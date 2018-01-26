package com.zyh.controller.mainCode;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyh.controller.base.BaseController;
import com.zyh.domain.mainCode.User;
import com.zyh.domain.mainCode.UserLogin;
import com.zyh.service.mainCode.IUserLoginService;
import com.zyh.service.mainCode.IUserService;
import com.zyh.vo.base.JsonResult;

/**
 * 登录用户管理模块
 *
 * @author 1101399
 * @CreateDate: 2017-12-27 上午9:16:08
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

	@Resource
	private IUserLoginService userLonginService;
	@Resource
	private IUserService userService;

	@RequestMapping("/list")
	public String list(ModelMap model){
		List<UserLogin> userList = userLonginService.findAll();
		String caption = "这是部分用户的信息，此部分供权限用户使用";
		model.put("userList", userList);
		model.put("caption", caption);
		return "mainCode/userLogin/list";
	}

	// 暂时挂起
	@RequestMapping("/admin")
	public void select(ModelMap model){

	}

	@RequestMapping("/add")
	public String add(ModelMap model){
	    User user = new User();
	    model.put("user", user);
	    return "common/user/edit";
	}

	@RequestMapping("/edit")
	public String edit(@RequestParam Integer id,ModelMap model){
	    User user = userService.findById(id);
	    model.put("user", user);
	    return "common/user/edit";
	}

	@RequestMapping("/save")
	public JsonResult<String> save(){
	    return new JsonResult<String>(true,"OK");
	}

	@RequestMapping("/delete")
	@ResponseBody
	public JsonResult<String> delete(@RequestParam Integer id){
	    userService.deleteById(id);
	    userLonginService.deleteById(id);
	    return new JsonResult<String>(true, "删除成功");
	}
}
