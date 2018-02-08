package com.zyh.service.mainCode.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.zyh.dao.base.MybatisMapper;
import com.zyh.dao.mainCode.UserLoginMapper;
import com.zyh.domain.mainCode.UserLogin;
import com.zyh.service.base.impl.BaseServiceImpl;
import com.zyh.service.mainCode.IUserLoginService;

/**
 * Service层： UserLoginServiceImpl->用户登录信息的操作接口实现
 *
 * @author 1101399
 * @CreateDate: 2017-12-27 上午9:53:39
 */
@Service
public class UserLoginServiceImpl extends BaseServiceImpl<UserLogin, Integer> implements
        IUserLoginService {

    private UserLoginMapper userLoginMapper;

    @Override
    @Resource(name = "mainCode.UserLoginMapper")
    public void setMybatisMapper(MybatisMapper<UserLogin, Integer> entityMapper) {
        this.userLoginMapper = (UserLoginMapper) entityMapper;
        this.mybatisMapper = entityMapper;
    }

    /**
     * 用户登录验证
     */
    @Override
    public boolean loginSystem(String userNo, String userPassword) {
        boolean userLoginOK = true;
        // ciphertext 密文加密
        String ciphertext = DigestUtils.md5DigestAsHex(userPassword.getBytes());
        Integer number = userLoginMapper.loginSystem(userNo, ciphertext);
        if (number.equals(1)) {
            userLoginOK = true;
        } else {
            userLoginOK = false;
        }
        return userLoginOK;
    }

    /**
     * 用户登录类查询
     */
    @Override
    public UserLogin findByNameAndPass(String userNo, String userPassword) {
        String ciphertext = DigestUtils.md5DigestAsHex(userPassword.getBytes());
        return userLoginMapper.findByNameAndPass(userNo, ciphertext);
    }

    // MD5加密
    // String oldPasswd = DigestUtils.md5DigestAsHex(oldPasswd.getBytes());
}
