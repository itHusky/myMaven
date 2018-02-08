package com.zyh.service.mainCode.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyh.dao.base.MybatisMapper;
import com.zyh.dao.mainCode.PermissionsChildMapper;
import com.zyh.domain.mainCode.PermissionsChild;
import com.zyh.service.base.impl.BaseServiceImpl;
import com.zyh.service.mainCode.IPermissionsChildService;

@Service
public class PermissionsChildService extends BaseServiceImpl<PermissionsChild, Integer> implements IPermissionsChildService{

    private PermissionsChildMapper permissionsChildMapper;

    @Override
    @Resource(name = "mainCode.PermissionsChildMapper")
    public void setMybatisMapper(MybatisMapper<PermissionsChild, Integer> entityMapper) {
        // TODO Auto-generated method stub
        this.permissionsChildMapper = (PermissionsChildMapper) entityMapper;
        this.mybatisMapper = entityMapper;
    }

}
