package com.users.modules.user.service.impl;



import com.common.entity.user.CrUserRole;
import com.common.entity.user.CrUserRoleExample;
import com.users.component.util.CheckUtil;
import com.common.entity.role.CrSysRole;
import com.common.entity.role.CrSysRoleExample;
import com.users.modules.mapper.primary.role.CrSysRoleDAO;
import com.users.modules.user.entity.CrUser;
import com.users.modules.user.entity.CrUserExample;
import com.users.modules.mapper.primary.user.CrUserMapper;
import com.users.modules.mapper.primary.user.CrUserRoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangchao on 2019/2/27.
 */
@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private CrUserMapper userDAO;

    @Autowired
    private CrUserRoleDAO userRoleDAO;

    @Autowired
    private CrSysRoleDAO sysRoleDAO;

    @Override
    public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {
        CrUser user = null;
        //按照用户名查找
        CrUserExample example = new CrUserExample();
        if (CheckUtil.isMobile(userAccount)) {//是手机号
            example.createCriteria().andPhoneEqualTo(userAccount);
        }else if (CheckUtil.isEmail(userAccount)) {//是邮箱
            example.createCriteria().andEmailEqualTo(userAccount);
        }else{
            //return null;
            example.createCriteria().andAccountEqualTo(userAccount);
        }
        //example.createCriteria().andUserNameEqualTo(username);

        List<CrUser> list = userDAO.selectByExample(example);
        if (list != null && list.size() > 0) {
            user = list.get(0);
            // 权限
            Long id = user.getId();
            CrUserRoleExample userRoleExample = new CrUserRoleExample();
            userRoleExample.createCriteria().andUserIdEqualTo(id);
            //用户所有的权限编号
            List<CrUserRole> roleIds = userRoleDAO.selectByExample(userRoleExample);
            if (roleIds != null && roleIds.size() > 0) {
                List<Long> ids = new ArrayList<>();
                for (CrUserRole userRole : roleIds) {
                    ids.add(userRole.getRoleId());
                }

                // 查询权限
                CrSysRoleExample sysRoleExample = new CrSysRoleExample();
                sysRoleExample.createCriteria().andRoleIdIn(ids);

                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                List<CrSysRole> roleList = sysRoleDAO.selectByExample(sysRoleExample);
                if (roleList != null && roleList.size() > 0) {
                    for (CrSysRole role : roleList) {
                        grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleMean()));
                    }
                }
                user.setAuths(grantedAuthorities);
            }
        }
        return user;
    }

}

