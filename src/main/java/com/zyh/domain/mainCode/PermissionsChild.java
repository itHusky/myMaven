package com.zyh.domain.mainCode;

import java.io.Serializable;

/**
 * 子级权限列表
 *
 * @author 1101399
 * @CreateDate: 2018-1-4 下午5:18:37
 */
public class PermissionsChild implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	private Integer permissionId;// 权限ID
	private Permissions permissionPId;// 父级权限ID
	private String Permissions;// 权限名称
	private String description;// 权限描述
	private Integer available;// 是否允许启用

	/**
	 * 不启用
	 */
	public final static int PER_C_ENABLES = 0;

	/**
	 * 启用
	 */
	public final static int PER_C_NOT_ENABLES = 1;

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public Permissions getPermissionPId() {
		return permissionPId;
	}

	public void setPermissionPId(Permissions permissionPId) {
		this.permissionPId = permissionPId;
	}

	public String getPermissions() {
		return Permissions;
	}

	public void setPermissions(String permissions) {
		Permissions = permissions;
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
