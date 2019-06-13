package com.users.modules.role.service;

import com.common.entity.role.CrSysModule;
import com.common.entity.role.CrSysRole;

/**
 * Created by xiangchao on 2019/5/20.
 */
public interface ISysRoleService {
    /**
     * 根据用户编号获取权限信息
     */
    CrSysModule getRole(Long userId);
}
