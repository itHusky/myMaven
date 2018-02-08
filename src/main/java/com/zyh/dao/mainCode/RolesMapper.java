package com.zyh.dao.mainCode;

import org.springframework.stereotype.Repository;

import com.zyh.dao.base.MybatisMapper;
import com.zyh.domain.mainCode.Roles;

/**
 *  Dao层： RolesMapper->角色信息Service层对应的数据操作接口层
 *
 * @author      1101399
 * @CreateDate  2018-1-5 下午2:42:52
 */
@Repository("mainCode.RolesMapper")
public interface RolesMapper  extends MybatisMapper<Roles, Integer>{

}
