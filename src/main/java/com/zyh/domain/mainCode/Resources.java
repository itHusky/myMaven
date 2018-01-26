package com.zyh.domain.mainCode;

import java.io.Serializable;

/**
 * URL资源列表
 *
 * @author 1101399
 * @CreateDate: 2018-1-4 下午5:01:05
 */
public class Resources implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	private Integer resourceId;// 资源ID
	private String Resources;// 资源URL
	private String description;// 资源描述
	private Integer available;// 是否允许启用

	/**
	 * 不启用
	 */
	public final static int RES_ENABLES = 0;

	/**
	 * 启用
	 */
	public final static int RES_NOT_ENABLES = 1;

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getResources() {
		return Resources;
	}

	public void setResources(String resources) {
		Resources = resources;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

}
