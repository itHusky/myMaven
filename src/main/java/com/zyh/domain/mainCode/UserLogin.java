package com.zyh.domain.mainCode;

import java.io.Serializable;

/**
 * 用户登录
 *
 * @author 1101399
 * @CreateDate: 2017-11-16 上午10:25:43
 */
public class UserLogin implements Serializable {

	/**
	 * 序列化的序列号
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;		//用户id
	private String userNo;		//用户编码
	private String userName;	//用户昵称
	private String userPassword;//用户密码

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
