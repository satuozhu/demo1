package com.users.modules.mapper.primary.user;

import com.common.entity.user.CrSysArea;
import com.common.entity.user.CrSysAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrSysAreaMapper {
    long countByExample(CrSysAreaExample example);

    int deleteByExample(CrSysAreaExample example);

    int deleteByPrimaryKey(String areaId);

    int insert(CrSysArea record);

    int insertSelective(CrSysArea record);

    List<CrSysArea> selectByExample(CrSysAreaExample example);

    CrSysArea selectByPrimaryKey(String areaId);

    int updateByExampleSelective(@Param("record") CrSysArea record, @Param("example") CrSysAreaExample example);

    int updateByExample(@Param("record") CrSysArea record, @Param("example") CrSysAreaExample example);

    int updateByPrimaryKeySelective(CrSysArea record);

    int updateByPrimaryKey(CrSysArea record);
}