package com.zyh.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zyh.domain.base.Dictionary;
import com.zyh.service.base.IDictionaryService;
import com.zyh.service.base.impl.DictionaryServiceImpl;

public class DictionaryTag extends BodyTagSupport {

	/**
	 * (序列化)验证版本一致性
	 */
	private static final long serialVersionUID = 1L;

	private final String TYPE_SELECT = "SELECT";// 下拉框
	private final String TYPE_CHECKBOX = "CHECKBOX";// 复选框
	private final String TYPE_RADIO = "RADIO";// 单选框
	private final String TYPE_TEXT = "TEXT";// 文本

	private String type;// 页面样式

	private String group;// 数据字典分组
	private String name = "";// 这里的name是表单字段名称，不是类Dictionary中的name
	private Boolean allowEmpty = true;// 默认允许为空，则会自动添加一行空选项“<option>defaultText值</option>”
	private String defaultText = "";// 当allEmpty=true时，则下拉列表框的空值对应的文本为defaultText
	private String value = null;// 默认选中值。设为字符串，是为了设置复选框多选值
	private String cssClass = "";// CSS对应的class名

	private IDictionaryService dicService;
	private List<Dictionary> datasource = new ArrayList<Dictionary>();

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getDefaultText() {
		return defaultText;
	}

	public void setDefaultText(String defaultText) {
		this.defaultText = defaultText;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getAllowEmpty() {
		return allowEmpty;
	}

	public void setAllowEmpty(Boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<Dictionary> getDatasource() {
		return datasource;
	}

	@Override
	public int doStartTag() throws JspTagException {
		ServletContext servletContext = pageContext.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		dicService = wac.getBean(DictionaryServiceImpl.class);

		// 获取数据源
		if (datasource == null || datasource.isEmpty()) {
			datasource = dicService.findByGroup(group);
		}

		if (type == null) {
			type = TYPE_SELECT;
		} else {
			type = type.toUpperCase();
		}

		StringBuffer sb = new StringBuffer();
		if (type.equals(TYPE_TEXT)) {
			String name = dicService.getName(group, value);
			sb.append(name == null ? "" : name);
		} else if (type.equals(TYPE_CHECKBOX)) {
			sb = createCheckBoxGroup();
		} else if (type.equals(TYPE_RADIO)) {
			sb = createRadioGroup();
		} else {
			sb = createSelectGroup(value);
		}

		try {
			pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			throw new JspTagException(e);
		}

		return EVAL_BODY_INCLUDE;
	}

	/**
	 * 创建复选框组
	 *
	 * @return
	 */
	private StringBuffer createCheckBoxGroup() {
		String[] arrValue = value == null ? new String[] {} : value.split(",");

		StringBuffer sbHtml = new StringBuffer();
		for (int i = 0; i < datasource.size(); i++) {
			Dictionary dic = datasource.get(i);
			String dicName = dic.getName();
			String dicValue = String.valueOf(dic.getValue());

			sbHtml.append("<div class='").append(cssClass).append("'><label>")
					.append("<input type='checkbox' name='").append(getName())
					.append("' value='").append(dicValue);
			if (ArrayUtils.contains(arrValue, dicValue)) {
				sbHtml.append("' cheacked />");
			} else {
				sbHtml.append("' />");
			}
			sbHtml.append(dicName).append("</div></label>");
		}
		return sbHtml;
	}

	/**
	 * 创建单选框组
	 *
	 * @return
	 */
	private StringBuffer createRadioGroup() {
		StringBuffer sbHtml = new StringBuffer();
		for (int i = 0; i < datasource.size(); i++) {
			Dictionary dic = datasource.get(i);
			// TODO 代码暂缺
			// TODO Auto-generated method stub
		}

		return sbHtml;
	}

	/**
	 * 创建下拉列表框组
	 *
	 * @param value
	 * @return
	 */
	private StringBuffer createSelectGroup(String value) {
		StringBuffer sbHtml = new StringBuffer();
		for (int i = 0; i < datasource.size(); i++) {
			// TODO 代码暂缺
			// TODO Auto-generated method stub
		}

		return sbHtml;
	}
}
