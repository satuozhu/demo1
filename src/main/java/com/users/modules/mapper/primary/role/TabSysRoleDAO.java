package com.users.modules.mapper.primary.role;

import com.common.entity.role.TabSysRole;
import com.common.entity.role.TabSysRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TabSysRoleDAO {
    long countByExample(TabSysRoleExample example);

    int deleteByExample(TabSysRoleExample example);

    int deleteByPrimaryKey(Long roleId);

    int insert(TabSysRole record);

    int insertSelective(TabSysRole record);

    List<TabSysRole> selectByExample(TabSysRoleExample example);

    TabSysRole selectByPrimaryKey(Long roleId);

    int updateByExampleSelective(@Param("record") TabSysRole record, @Param("example") TabSysRoleExample example);

    int updateByExample(@Param("record") TabSysRole record, @Param("example") TabSysRoleExample example);

    int updateByPrimaryKeySelective(TabSysRole record);

    int updateByPrimaryKey(TabSysRole record);
}