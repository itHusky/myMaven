package com.zyh.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 自定义处理字符串转换为日期。
 *
 * <p>
 * 若日期字符串的长度为10，则默认日期字符串的格式为：yyyy-MM-dd
 * </p>
 * <p>
 * 若日期字符串的长度为16，则默认日期字符串的格式为：yyyy-MM-dd HH:mm
 * </p>
 * <p>
 * 否则，统一按默认格式（yyyy-MM-dd HH:mm:ss）转换
 * </p>
 *
 *
 * @author 1101399
 * @CreateDate: 2017-11-29 上午8:30:50
 */
public class CustomDateFormat extends SimpleDateFormat {

	private static final long serialVersionUID = 1L;

	/**
	 * 调用父类的函数
	 * 好处：减少了目标类的一个继承（避免了一个类继承多个父类）
	 */
	public CustomDateFormat(String s){
		super(s);
	}

	@Override
	public java.util.Date parse(String text) throws ParseException{
		if (text == null){
			return null;
		}
		int length = text.trim().length();
		if (length == 10){
			return new java.util.Date(new SimpleDateFormat("yyyy-MM-dd").parse(text).getTime());
		} else if (length == 16) {
			return new java.util.Date(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(text)
					.getTime());
		} else {
			return new java.util.Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(text)
					.getTime());
		}
	}
}
