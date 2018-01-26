package com.zyh.domain.mainCode;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author 1101399
 * @CreateDate: 2018-1-4 下午4:25:39
 */
public class User implements Serializable {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    private Integer id;
    private UserLogin user;
    private Roles roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserLogin getUser() {
        return user;
    }

    public void setUser(UserLogin user) {
        this.user = user;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

}
