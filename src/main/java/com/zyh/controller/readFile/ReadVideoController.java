package com.zyh.controller.readFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zyh.controller.base.BaseController;

/**
 * 视频list与显示
 *
 * @author 1101399
 * @CreateDate: 2017-12-25 下午1:59:18
 */
@Controller
@RequestMapping("/video")
public class ReadVideoController extends BaseController{

	private final Logger log = LoggerFactory.getLogger(ReadVideoController.class);

	/**
	 * LIST列表
	 */
	@RequestMapping("/list")
	public String list(ModelMap model){

		List<File> fileList1 = new ArrayList<File>();

//		File temFile = new File(WebContextUtils.getWebRootPath()+ "WEB-INF\\jsp\\wareHouse");
		File temFile = new File("E:\\report");
		File[] fileList = temFile.listFiles();

//		System.out.println("manager.mp4".substring("manager.mp4".lastIndexOf(".") + 1));
		//使用split函数分离文件后缀名失败 在只用 . 时分离失败
		//疑似特殊含义字符导致   未测试  具体原因不清楚

		for(File file:fileList){
			String name = file.getName();
			String suffix = name.substring(file.getName().lastIndexOf(".") + 1);
			System.out.println(suffix);
/*			if(suffix.equals("avi")){
				log.info("test"+file.getName());
				System.out.println(file.getName());
				fileList1.add(file);
			}if(suffix.equals("mp4")){
				log.info("test"+file.getName());
				System.out.println(file.getName());
				fileList1.add(file);
			}if(suffix.equals("rm")){
				log.info("test"+file.getName());
				System.out.println(file.getName());
				fileList1.add(file);
			}*/
			if(suffix.equals("avi") || suffix.equals("mp4")){
				log.info("test "+file.getName());
				System.out.println(file.getName());
				fileList1.add(file);
			}
		}


		model.put("fileList", fileList1);

		return "file/video/list";
	}

	/**
	 * SHOW展示
	 */
	@RequestMapping("show")
	public String show(String name,ModelMap model){
		model.put("test", name);
		return "file/video/show";
	}

}
