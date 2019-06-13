package com.users.modules.mapper.primary.role;

import com.common.entity.role.CrSysRoleMenu;
import com.common.entity.role.CrSysRoleMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrSysRoleMenuDAO {
    long countByExample(CrSysRoleMenuExample example);

    int deleteByExample(CrSysRoleMenuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrSysRoleMenu record);

    int insertSelective(CrSysRoleMenu record);

    List<CrSysRoleMenu> selectByExample(CrSysRoleMenuExample example);

    CrSysRoleMenu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrSysRoleMenu record, @Param("example") CrSysRoleMenuExample example);

    int updateByExample(@Param("record") CrSysRoleMenu record, @Param("example") CrSysRoleMenuExample example);

    int updateByPrimaryKeySelective(CrSysRoleMenu record);

    int updateByPrimaryKey(CrSysRoleMenu record);
}