package com.zyh.service.mainCode.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyh.dao.base.MybatisMapper;
import com.zyh.dao.mainCode.PermissionsResourceMapper;
import com.zyh.domain.mainCode.PermissionsResource;
import com.zyh.service.base.impl.BaseServiceImpl;
import com.zyh.service.mainCode.IPermissionsResourceService;

@Service
public class PermissionsResourceServiceImpl extends BaseServiceImpl<PermissionsResource, Integer> implements IPermissionsResourceService{

    private PermissionsResourceMapper permissionsResourceMapper;

    @Override
    @Resource(name = "mainCode.PermissionsResourceMapper")
    public void setMybatisMapper(MybatisMapper<PermissionsResource, Integer> entityMapper) {
        // TODO Auto-generated method stub
        this.permissionsResourceMapper = (PermissionsResourceMapper) entityMapper;
        this.mybatisMapper = entityMapper;
    }

    /**
     * 权限ID查询对应的资源列表
     */
    @Override
    public List<PermissionsResource> findByPermissionId(Integer permissionId) {
        // TODO Auto-generated method stub
        return permissionsResourceMapper.findByPermissionId(permissionId);
    }

}
