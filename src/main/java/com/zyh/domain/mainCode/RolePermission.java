package com.zyh.domain.mainCode;

import java.io.Serializable;

/**
 * 角色权限对应表
 *
 * @author 1101399
 * @CreateDate: 2018-1-4 下午4:27:48
 */
public class RolePermission implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	private Roles roles;// 角色
	private PermissionsChild permissions;// 权限

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public PermissionsChild getPermissions() {
		return permissions;
	}

	public void setPermissions(PermissionsChild permissions) {
		this.permissions = permissions;
	}
}
