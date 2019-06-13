package com.users.modules.mapper.primary.role;

import com.common.entity.role.CrSysRole;
import com.common.entity.role.CrSysRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrSysRoleDAO {
    long countByExample(CrSysRoleExample example);

    int deleteByExample(CrSysRoleExample example);

    int deleteByPrimaryKey(Long roleId);

    int insert(CrSysRole record);

    int insertSelective(CrSysRole record);

    List<CrSysRole> selectByExample(CrSysRoleExample example);

    CrSysRole selectByPrimaryKey(Long roleId);

    int updateByExampleSelective(@Param("record") CrSysRole record, @Param("example") CrSysRoleExample example);

    int updateByExample(@Param("record") CrSysRole record, @Param("example") CrSysRoleExample example);

    int updateByPrimaryKeySelective(CrSysRole record);

    int updateByPrimaryKey(CrSysRole record);
}