package com.users.modules.role.service.impl;

import com.common.entity.role.CrSysModule;
import com.common.entity.role.CrSysRoleMenu;
import com.common.entity.role.CrSysRoleMenuExample;
import com.common.entity.user.CrUserRole;
import com.common.entity.user.CrUserRoleExample;
import com.users.modules.mapper.primary.role.CrSysRoleMenuDAO;
import com.users.modules.role.service.ISysModuleService;
import com.users.modules.role.service.ISysRoleService;
import com.users.modules.mapper.primary.user.CrUserRoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangchao on 2019/5/20.
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {
    @Autowired
    private CrUserRoleDAO userRoleMapper;
    @Autowired
    private CrSysRoleMenuDAO roleMenuDAO;
    @Autowired
    private ISysModuleService moduleService;

    @Override
    public CrSysModule getRole(Long userId) {
        //根据userId获取role
        CrUserRoleExample userRoleExample = new CrUserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(userId);
        CrUserRole userRole =  userRoleMapper.selectByExample(userRoleExample).get(0);
        //根据role获取menu
        CrSysRoleMenuExample sysRoleExample = new CrSysRoleMenuExample();
        sysRoleExample.createCriteria().andRoleIdEqualTo(userRole.getId());
        List<CrSysRoleMenu> menuList = roleMenuDAO.selectByExample(sysRoleExample);
        //根据menu获取module
        List<Long> ids = new ArrayList<>();
        for (CrSysRoleMenu item:menuList) {
            ids.add(item.getMenuId());
        }
        CrSysModule menuRoot = moduleService.getModuleList(ids);

        return menuRoot;
    }
}
