package com.users.modules.mapper.primary.user;

import com.users.modules.user.entity.CrUser;
import com.users.modules.user.entity.CrUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrUserMapper {
    long countByExample(CrUserExample example);

    int deleteByExample(CrUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrUser record);

    int insertSelective(CrUser record);

    List<CrUser> selectByExampleWithBLOBs(CrUserExample example);

    List<CrUser> selectByExample(CrUserExample example);

    CrUser selectByPrimaryKey(Long id);
    com.common.entity.user.CrUser selectByPrimaryKey2(Long id);

    int updateByExampleSelective(@Param("record") CrUser record, @Param("example") CrUserExample example);

    int updateByExampleWithBLOBs(@Param("record") CrUser record, @Param("example") CrUserExample example);

    int updateByExample(@Param("record") CrUser record, @Param("example") CrUserExample example);

    int updateByPrimaryKeySelective(CrUser record);

    int updateByPrimaryKeyWithBLOBs(CrUser record);

    int updateByPrimaryKey(CrUser record);
}