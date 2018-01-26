package com.zyh.controller.mainCode;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyh.controller.base.BaseController;

@Controller
@RequestMapping("/login")
public class Appp extends BaseController {

	@RequestMapping("/index")
	@ResponseBody
	public String index() {
		System.out.println("aaa");
		return "login/index";
	}
}