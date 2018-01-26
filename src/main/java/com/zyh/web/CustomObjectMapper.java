package com.zyh.web;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 *  解决SpringMVC使用注解@ResponseBody返回json时，日期格式默认显示为
 *  时间戳的问题。这个需要配合Spring配置文件spring-context.xml中
 *  <mvc:message-converters>使用
 *
 * @author 1101399
 * @CreateDate: 2017-12-5 上午11:31:35
 */
@Component("customObjectMapper")
public class CustomObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	public CustomObjectMapper(){
		this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}
}
