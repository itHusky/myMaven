package com.zyh.dao.mainCode;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zyh.dao.base.MybatisMapper;
import com.zyh.domain.mainCode.RolePermission;

/**
 * Dao层： RolePermissionMapper->角色权限对应表信息Service层对应的数据操作接口层
 *
 * @author      1101399
 * @CreateDate  2018-1-26 下午5:28:35
 */
@Repository("mainCode.RolePermissionMapper")
public interface RolePermissionMapper extends MybatisMapper<RolePermission, Integer>{

    /**
     * 角色ID查询对应的权限对应信息
     */
    public RolePermission findByRolesId(@Param("roleId") Integer roleId);

}
