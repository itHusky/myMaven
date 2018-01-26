package com.zyh.domain.mainCode;

import java.io.Serializable;

/**
 * 权限资源对应表
 *
 * @author 1101399
 * @CreateDate: 2018-1-4 下午5:06:17
 */
public class PermissionsResource implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	private Permissions permissions;// 权限
	private Resources resources;// 资源

	public Permissions getPermissions() {
		return permissions;
	}

	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
	}

	public Resources getResources() {
		return resources;
	}

	public void setResources(Resources resources) {
		this.resources = resources;
	}

}
