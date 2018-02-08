package com.zyh.service.mainCode;

import java.util.List;

import com.zyh.domain.mainCode.PermissionsResource;
import com.zyh.service.base.IBaseService;

/**
 * 权限资源service层接口
 *
 * @author      1101399
 * @CreateDate  2018-1-12 上午8:49:01
 */
public interface IPermissionsResourceService extends IBaseService<PermissionsResource, Integer>{

    /**
     * 权限ID查询对应的资源列表
     *
     * @param permissionId
     * @return
     */
    public List<PermissionsResource> findByPermissionId(Integer permissionId);

}
