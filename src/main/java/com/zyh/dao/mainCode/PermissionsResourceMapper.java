package com.zyh.dao.mainCode;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zyh.dao.base.MybatisMapper;
import com.zyh.domain.mainCode.PermissionsResource;

@Repository("mainCode.PermissionsResourceMapper")
public interface PermissionsResourceMapper extends MybatisMapper<PermissionsResource, Integer>{

    /**
     * 权限ID查询对应的资源列表
     */
    public List<PermissionsResource> findByPermissionId(@Param("permissionId") Integer permissionId);

}
