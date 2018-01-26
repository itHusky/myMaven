package com.zyh.service.mainCode.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyh.dao.base.MybatisMapper;
import com.zyh.dao.mainCode.UserMapper;
import com.zyh.domain.mainCode.User;
import com.zyh.domain.mainCode.UserLogin;
import com.zyh.service.base.impl.BaseServiceImpl;
import com.zyh.service.mainCode.IUserService;

/**
 * Service层： UserServiceImpl->用户信息的操作接口实现
 *
 * @author 1101399
 * @CreateDate: 2018-1-5 上午8:31:41
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements IUserService {

    private UserMapper userMapper;

    @Override
    @Resource(name = "mainCode.UserMapper")
    public void setMybatisMapper(MybatisMapper<User, Integer> entityMapper) {
        this.userMapper = (UserMapper) entityMapper;
        this.mybatisMapper = entityMapper;
    }

    /**
     * 用户ID查询用户信息
     */
    @Override
    public User userInfoById(Integer userId) {
        // TODO Auto-generated method stub
        return userMapper.userInfoById(userId);
    }

    /**
     * 用户NO查询用户信息
     */
    @Override
    public User userInfoByNo(String userNO) {
        // TODO Auto-generated method stub
        return userMapper.userInfoByNo(userNO);
    }

    /**
     * 用户NAME查询用户信息
     */
    @Override
    public User userInfoByName(String userName) {
        // TODO Auto-generated method stub
        return userMapper.userInfoByName(userName);
    }

    /**
     * 用户Role查询用户信息
     */
    @Override
    public List<User> userInfoByRoleId(Integer roleId) {
        // TODO Auto-generated method stub
        return userMapper.userInfoByRoleId(roleId);
    }

    /**
     * 用户对应角色唯一性验证
     */
    @Override
    public int getCount(Integer userId, Integer roleId) {
        return userMapper.getCount(userId, roleId);
    }

    /**
     * <h3>通过用户登录信息查询用户信息</h3>
     */
    @Override
    public User findByUserLogin(UserLogin userLogin) {
        return userMapper.findByUserLogin(userLogin);
    }

}
