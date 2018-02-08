package com.zyh.dao.mainCode;

import org.springframework.stereotype.Repository;

import com.zyh.dao.base.MybatisMapper;
import com.zyh.domain.mainCode.PermissionsChild;

@Repository("mainCode.PermissionsChildMapper")
public interface PermissionsChildMapper extends MybatisMapper<PermissionsChild, Integer>{

}
