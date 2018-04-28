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
     *
     * MD5不可逆的原因是其是一种散列函数，使用的是hash算法， 在计算过程中原文的部分信息是丢失了的，不过有个地方值得指出的是，
     * 一个MD5理论上的确是可能对应无数多个原文的， 因为MD5是有限多个的而原文可以是无数多个。
     * 比如主流使用的MD5将任意长度的“字节串映射为一个128bit的大整数。
     * 也就是一共有2^128种可能，大概是3.4*10^38，这个数字是有限多个的，
     * 而但是世界上可以被用来加密的原文则会有无数的可能性。不过需要注意的一点是，
     * 尽量这是一个理论上的有限对无限，不过问题是这个无限在现实生活中并不完全成立，
     * 因为一方面现实中原文的长度往往是有限的（以常用的密码为例，一般人都在20位以内），
     * 另一方面目前想要发现两段原文对应同一个MD5（专业的说这叫杂凑冲撞）值非常困难，
     * 因此某种意义上来说，在一定范围内想构建MD5值与原文的一一对应关系是完全有可能的。 所以对于MD5目前最有效的攻击方式就是彩虹表_百度百科。
     *
     */
    @Override
    public boolean loginSystem(String userNo, String userPassword){
        boolean userLoginOK = true;
        Integer number = 0;
        // ciphertext 密文加密
        String ciphertext = DigestUtils.md5DigestAsHex(userPassword.getBytes());
        System.out.println(ciphertext);

        // TODO XXX Invalid bound statement (not found) 所有的数据库连接都会显示着个异常
        // 推测应该是因为 访问MyBatis不到数据库所致 数据库连接的URL 账号和密码都是正确的
        try {
            number = userLoginMapper.loginSystem(userNo, ciphertext);
        } catch (Exception e) {
            e.printStackTrace();
//            throw new BusinessException(e.getMessage());
        }

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
