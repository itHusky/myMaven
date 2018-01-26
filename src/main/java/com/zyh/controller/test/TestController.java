package com.zyh.controller.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zyh.controller.base.BaseController;

@Controller
@RequestMapping(value = "/login")
public class TestController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(TestController.class);

//	@ResponseBody	//将return的返回值返回到jsp界面

	@RequestMapping("/test")
	public String test(ModelMap model) {
		String xx = "$$$$$$$$$$$$$";
		model.put("testURL", xx);
		log.error("test:" + xx);
		return "login/test";
	}
}
