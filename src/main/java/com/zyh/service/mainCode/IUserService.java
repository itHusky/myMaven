package com.zyh.service.mainCode;

import java.util.List;

import com.zyh.domain.mainCode.User;
import com.zyh.domain.mainCode.UserLogin;
import com.zyh.service.base.IBaseService;

/**
 * Service层： IUserService->用户信息的操作接口层
 *
 * @author 1101399
 * @CreateDate: 2018-1-5 上午8:29:58
 */
public interface IUserService extends IBaseService<User, Integer> {

  /**
   * 用户ID查询用户信息
   */
  public User userInfoById(Integer userId);

  /**
   * 用户NO查询用户信息
   */
  public User userInfoByNo(String userNO);

  /**
   * 用户NAME查询用户信息
   */
  public User userInfoByName(String userName);

  /**
   * 用户Role查询用户信息
   */
  public List<User> userInfoByRoleId(Integer roleId);

  /**
   * 用户对应角色唯一性验证
   */
  public int getCount(Integer userId, Integer roleId);

  /**
   * 根据用户登录信息查找用户信息
   */
  public User findByUserLogin(UserLogin userLogin);
}
