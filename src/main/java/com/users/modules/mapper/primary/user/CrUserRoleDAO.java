package com.users.modules.mapper.primary.user;

import com.common.entity.user.CrUserRole;
import com.common.entity.user.CrUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrUserRoleDAO {
    long countByExample(CrUserRoleExample example);

    int deleteByExample(CrUserRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrUserRole record);

    int insertSelective(CrUserRole record);

    List<CrUserRole> selectByExample(CrUserRoleExample example);

    CrUserRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrUserRole record, @Param("example") CrUserRoleExample example);

    int updateByExample(@Param("record") CrUserRole record, @Param("example") CrUserRoleExample example);

    int updateByPrimaryKeySelective(CrUserRole record);

    int updateByPrimaryKey(CrUserRole record);
}