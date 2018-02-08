package com.zyh.service.mainCode.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyh.dao.base.MybatisMapper;
import com.zyh.dao.mainCode.PermissionsMapper;
import com.zyh.domain.mainCode.Permissions;
import com.zyh.service.base.impl.BaseServiceImpl;
import com.zyh.service.mainCode.IPermissionsService;

@Service
public class PermissionsServiceImpl extends BaseServiceImpl<Permissions, Integer> implements
        IPermissionsService {

    private PermissionsMapper permissionsMapper;

    @Override
    @Resource(name = "mainCode.PermissionsMapper")
    public void setMybatisMapper(MybatisMapper<Permissions, Integer> entityMapper) {
        // TODO Auto-generated method stub
        this.permissionsMapper = (PermissionsMapper) entityMapper;
        this.mybatisMapper = entityMapper;
    }
}
