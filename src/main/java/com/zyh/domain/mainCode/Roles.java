package com.zyh.domain.mainCode;

import java.io.Serializable;

/**
 * 角色列表
 *
 * @author 1101399
 * @CreateDate: 2018-1-4 下午4:11:06
 */
public class Roles implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	private Integer roleId;// 数据表ID
	private String role;// 角色名称
	private String description;// 角色描述
	private Integer available;// 是否允许启用

	/**
	 * 不启用
	 */
	public final static int ROLE_ENABLES = 0;

	/**
	 * 启用
	 */
	public final static int ROLE_NOT_ENABLES = 1;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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
