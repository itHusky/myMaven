package com.zyh.service.mainCode.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyh.dao.base.MybatisMapper;
import com.zyh.dao.mainCode.RolesMapper;
import com.zyh.domain.mainCode.Roles;
import com.zyh.service.base.impl.BaseServiceImpl;
import com.zyh.service.mainCode.IRolesService;

/**
 * Service层： RolesServiceImpl->角色信息的操作接口实现
 *
 * @author 1101399
 * @CreateDate 2018-1-26 下午3:04:49
 */
@Service
public class RolesServiceImpl extends BaseServiceImpl<Roles, Integer> implements IRolesService {

    private RolesMapper rolesMapper;

    @Override
    @Resource(name = "mainCode.RolesMapper")
    public void setMybatisMapper(MybatisMapper<Roles, Integer> entityMapper) {
        this.rolesMapper = (RolesMapper) entityMapper;
        this.mybatisMapper = entityMapper;

    }

}
