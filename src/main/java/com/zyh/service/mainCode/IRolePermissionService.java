package com.zyh.service.mainCode;

import com.zyh.domain.mainCode.RolePermission;
import com.zyh.service.base.IBaseService;

/**
 * 角色权限service层接口
 *
 * @author      1101399
 * @CreateDate  2018-1-12 上午8:47:26
 */
public interface IRolePermissionService extends IBaseService<RolePermission, Integer>{

    /**
     * 角色ID查询对应的权限对应信息
     *
     * @param roleId
     * @return
     */
    public RolePermission findByRolesId(Integer roleId);

}
