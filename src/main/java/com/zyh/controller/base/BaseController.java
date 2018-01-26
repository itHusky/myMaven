package com.zyh.controller.base;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.zyh.web.CustomDateFormat;
import com.zyh.web.CustomNativeEditor;

/**
 * 基础控制器
 *
 * @author 1101399
 * @CreateDate: 2017-11-28 下午3:38:43
 */
public class BaseController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// binder.registerCustomEditor(String.class, new
		// StringEscapeEditor(false));
		binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(
				new CustomDateFormat("yyyy-MM-dd"), true));
		binder.registerCustomEditor(int.class, null, new CustomNativeEditor(Integer.class, null,
				true));
		binder.registerCustomEditor(long.class, null,
				new CustomNativeEditor(Long.class, null, true));
		binder.registerCustomEditor(float.class, new CustomNumberEditor(Float.class, true));
		binder.registerCustomEditor(float.class, null, new CustomNativeEditor(Float.class, null,
				true));
		binder.registerCustomEditor(BigDecimal.class,
				new CustomNumberEditor(BigDecimal.class, true));
		binder.registerCustomEditor(Boolean.class, null, new CustomBooleanEditor(true));
	}

	/**
	 * 验证IP格式是否正确
	 *
	 * @param ip
	 *            IP地址
	 * @return true-验证为IP地址
	 */
	protected boolean validateIP(String ip) {
		if (StringUtils.isBlank(ip)) {
			return false;
		}
		String regex = "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\\."
				+ "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\."
				+ "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\."
				+ "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])";
		return ip.matches(regex);
	}
}
