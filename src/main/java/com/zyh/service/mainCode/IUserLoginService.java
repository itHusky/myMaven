package com.zyh.service.mainCode;

import com.zyh.domain.mainCode.UserLogin;
import com.zyh.service.base.IBaseService;

/**
 * 用户登录service层接口
 *
 * @author 1101399
 * @CreateDate: 2017-12-27 上午9:53:59
 */
public interface IUserLoginService extends IBaseService<UserLogin, Integer> {

    /**
     * 用户登录验证
     */
    public boolean loginSystem(String userNo, String userPassword);

    /**
     * 用户登录类查询
     *
     * @param userNo
     *            用户编码
     * @param userPassword
     *            用户密码
     * @return
     */
    public UserLogin findByNameAndPass(String userNo, String userPassword);

}
