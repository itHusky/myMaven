package com.zyh.dao.mainCode;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zyh.dao.base.MybatisMapper;
import com.zyh.domain.mainCode.UserLogin;

/**
 * 用户登录
 *
 * @author 1101399
 * @CreateDate: 2017-11-16 上午10:47:17
 */
@Repository("mainCode.UserLoginMapper")
public interface UserLoginMapper extends MybatisMapper<UserLogin, Integer> {

    /**
     * 用户层持久管理层这个需要查一下加不加注释之间有什么区别
     *
     * 对应service.implement层中的注解
     *
     * @Repository("main.UserLogin")
     */

    /**
     * 通过用户名称查找相应信息(不允许相同的用户名存在)
     *
     * @param userName
     *            用户昵称
     * @return 用户昵称对应的信息
     */
    public UserLogin findByName(@Param("userName") String userName);

    /**
     * 通过用户编码账号查询相应的信息(不允许相同的用户编码存在)
     *
     * @param userNo
     *            用户编码
     * @return 用户编码对应的用户信息
     */
    public UserLogin findByNo(@Param("userNo") String userNo);

    /**
     * 修改用户密码
     *
     * @param userNo
     *            用户编码
     * @param userNewPassword
     *            用户新密码
     * @param userOldPassword
     *            用户旧密码
     */
    public void modifyPassword(@Param("userId") String userId,
            @Param("userNewPassword") String userNewPassword,
            @Param("userOldPassWord") String userOldPassword);

    /**
     * 用户登录验证
     *
     * @param userNo
     *            用户编码
     * @param userPassword
     *            用户密码(密文)
     * @return 1-用户通过验证 0-用户为通过验证 其他数字-用户验证通知系统管理员且不允许登录并进入账户验证界面
     */
    public int loginSystem(@Param("userNo") String userNo,
            @Param("userPassword") String userPassword);

    /**
     * 用户登录类查询
     *
     * @param userNo
     *            用户编码
     * @param ciphertext
     *            用户密码(密文)
     * @return
     */
    public UserLogin findByNameAndPass(@Param("userNo") String userNo,
            @Param("ciphertext") String ciphertext);
}
