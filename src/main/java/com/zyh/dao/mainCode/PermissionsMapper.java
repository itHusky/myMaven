package com.zyh.dao.mainCode;

import org.springframework.stereotype.Repository;

import com.zyh.dao.base.MybatisMapper;
import com.zyh.domain.mainCode.Permissions;

@Repository("mainCode.PermissionsMapper")
public interface PermissionsMapper extends MybatisMapper<Permissions, Integer>{

}
