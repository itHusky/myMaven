package com.zyh.domain.mainCode;

import java.io.Serializable;

/**
 * 权限资源对应表
 *
 * <pre>
 *  特殊权限的URL资源是通过正则表达式来进行管理的[允许全部过滤通过，或者是部分禁止通过]
 *  </pre><pre>
 *  非权限用户的URL验证是通过庞大的URL资源库来进行验证(这样以保证没有漏网之鱼)
 * </pre>
 *
 * @author 1101399
 * @CreateDate: 2018-1-4 下午5:06:17
 */
public class PermissionsResource implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	private PermissionsChild permissions;// 权限
	private Resources resources;// 资源

	public PermissionsChild getPermissions() {
		return permissions;
	}

	public void setPermissions(PermissionsChild permissions) {
		this.permissions = permissions;
	}

	public Resources getResources() {
		return resources;
	}

	public void setResources(Resources resources) {
		this.resources = resources;
	}

}
