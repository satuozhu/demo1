package com.users.modules.mapper.primary.user;

import com.common.entity.user.CrHouseTime;
import com.common.entity.user.CrHouseTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrHouseTimeMapper {
    long countByExample(CrHouseTimeExample example);

    int deleteByExample(CrHouseTimeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrHouseTime record);

    int insertSelective(CrHouseTime record);

    List<CrHouseTime> selectByExample(CrHouseTimeExample example);

    CrHouseTime selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrHouseTime record, @Param("example") CrHouseTimeExample example);

    int updateByExample(@Param("record") CrHouseTime record, @Param("example") CrHouseTimeExample example);

    int updateByPrimaryKeySelective(CrHouseTime record);

    int updateByPrimaryKey(CrHouseTime record);
}