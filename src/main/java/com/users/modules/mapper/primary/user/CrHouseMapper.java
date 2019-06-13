package com.users.modules.mapper.primary.user;

import java.util.List;

import com.common.entity.user.CrHouse;
import com.common.entity.user.CrHouseExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrHouseMapper {
    long countByExample(CrHouseExample example);

    int deleteByExample(CrHouseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrHouse record);

    int insertSelective(CrHouse record);

    List<CrHouse> selectByExample(CrHouseExample example);

    CrHouse selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrHouse record, @Param("example") CrHouseExample example);

    int updateByExample(@Param("record") CrHouse record, @Param("example") CrHouseExample example);

    int updateByPrimaryKeySelective(CrHouse record);

    int updateByPrimaryKey(CrHouse record);
}