package com.zyh.service.mainCode.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyh.dao.base.MybatisMapper;
import com.zyh.dao.mainCode.RolePermissionMapper;
import com.zyh.domain.mainCode.RolePermission;
import com.zyh.service.base.impl.BaseServiceImpl;
import com.zyh.service.mainCode.IRolePermissionService;

/**
 * Service层： RolePermissionServiceImpl->角色权限对应表信息的操作接口实现
 *
 * @author      1101399
 * @CreateDate  2018-1-26 下午5:24:28
 */
@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission, Integer> implements
        IRolePermissionService {

    private RolePermissionMapper rolePermissionMapper;

    @Override
    @Resource(name = "mainCode.RolePermissionMapper")
    public void setMybatisMapper(MybatisMapper<RolePermission, Integer> entityMapper) {
        // TODO Auto-generated method stub
        this.rolePermissionMapper = (RolePermissionMapper) entityMapper;
        this.mybatisMapper = entityMapper;
    }

    /**
     * 角色ID查询对应的权限对应信息
     */
    @Override
    public RolePermission findByRolesId(Integer roleId) {
        // TODO Auto-generated method stub
        return rolePermissionMapper.findByRolesId(roleId);
    }

}
