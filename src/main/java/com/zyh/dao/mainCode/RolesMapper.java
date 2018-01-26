package com.zyh.dao.mainCode;

import org.springframework.stereotype.Repository;

import com.zyh.dao.base.MybatisMapper;
import com.zyh.domain.mainCode.User;

/**
 *
 *
 * @author      1101399
 * @CreateDate  2018-1-5 下午2:42:52
 */
@Repository("mainCode.RolesMapper")
public interface RolesMapper  extends MybatisMapper<User, Integer>{

}
