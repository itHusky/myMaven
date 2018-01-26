package com.zyh.domain.base;

import java.io.Serializable;

/**
 * 数据字典
 *
 * @author 1101399
 * @CreateDate: 2017-11-24 上午10:31:07
 */
public class Dictionary implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;//流水号
	private String group;//分组类别
	private String name;//键值名称
	private String value;//键值

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
