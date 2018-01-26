package com.zyh.controller.readFile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zyh.controller.base.BaseController;
import com.zyh.util.WebContextUtils;

@Controller
@RequestMapping("/file")
public class ReadFileController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(ReadFileController.class);

	// @Resource
	// private IReadFile readFile;

	@SuppressWarnings("resource")
	@RequestMapping("/list")
	public String list(ModelMap model) {

		List<File> listFile = new ArrayList<File>();
		// List<String> listFile = new ArrayList<String>();

		File a = new File("C:/Users/1101399/Desktop/PDF");
		File[] array = a.listFiles();

		for (File file : array) {

			System.out.println("~~~~" + file.getName() + "!!!!"
					+ file.getPath() + "****" + file.length());
			if (file.getName().substring(file.getName().lastIndexOf(".") + 1)
					.equals("pdf")) {
				log.info("---------------" + file.getName());
				// listFile.add(file.getName());
				listFile.add(file);
			}

			if (file.getName().equals("MySQL性能调优与架构设计 PDF中文版全册.pdf")) {
				try {
					RandomAccessFile randomFile = new RandomAccessFile(
							"C:/Users/1101399/Desktop/PDF/" + file.getName(),
							"r");
					long fileLong = randomFile.length();
					log.debug("test:" + fileLong);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		log.info("............" + listFile.size());

		model.put("listFile", listFile);

		return "file/pdf/pdf";
	}

	@RequestMapping("/show")
	public String show(String name, ModelMap model) {
		String URL = WebContextUtils.getWebRootPath()+ "WEB-INF\\jsp\\wareHouse\\" + name;
		log.error(URL);
		log.error(name);
		File file = new File(URL);
		if (file.exists()) {
			model.put("name", name);
			System.out.println("name");
			return "file/pdf/login";
		} else {
			//文件不存在的
//			BusinessException xx = new BusinessException("文件这东西不存在的!");
//			throw new BusinessException("文件这东西不存在的!");
			//这块编码存在着一定的问题尚未解决、字符串name的编码是乱的、导致文件输出异常信息
			//现在我通过设置服务器Tomcat server.xml 文件来使get方法传递的URL编码为UTF-8
			//post方法则为request.setCharacterEncoding("UTF-8");
//			return "file/pdf/pdf";

			System.out.println(name);
			return "file/pdf/login";
		}
	}

	/**
	 * 这块是内部类的创建与使用
	 *
	 * @author 1101399
	 * @CreateDate: 2017-12-22 下午2:06:56
	 */
	@SuppressWarnings("unused")
	private	class BusinessException extends RuntimeException {

		/**
		 * 序列化编码
		 */
		private static final long serialVersionUID = 1L;

		private BusinessException(String message) {
			super(message);
		}
	}
}


