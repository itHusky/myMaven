package com.zyh.dao.mainCode;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zyh.dao.base.MybatisMapper;
import com.zyh.domain.mainCode.User;
import com.zyh.domain.mainCode.UserLogin;

/**
 * Dao层： UserMapper->用户信息Service层对应的数据操作接口层
 *
 * @author 1101399
 * @CreateDate: 2018-1-5 上午8:31:49
 */
@Repository("mainCode.UserMapper")
public interface UserMapper extends MybatisMapper<User, Integer> {

  /**
   * <b>用户ID</b>查询用户信息
   */
  public User userInfoById(@Param("userId") Integer userId);

  /**
   * 用户NO查询用户信息
   */
  public User userInfoByNo(@Param("userNO") String userNO);

  /**
   * 用户NAME查询用户信息
   */
  public User userInfoByName(@Param("userName") String userName);

  /**
   * 用户Role查询用户信息
   */
  public List<User> userInfoByRoleId(@Param("roleId") Integer roleId);

  /**
   * 同一个用户不允许拥有多种用户角色
   * <small>即用户与角色一一对应</small>
   * 确保唯一性验证
   */
  public int getCount(@Param("userId") Integer userId,@Param("roleId") Integer roleId);

  /**
   * <h3>通过用户登录信息查询用户信息</h3>
   */
  public User findByUserLogin(@Param("userLogin") UserLogin userLogin);

  public User findByUserLoginXXX(@Param("user") User user);

}
