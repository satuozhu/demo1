package com.users.modules.mapper.primary.user;

import com.common.entity.user.CrSysDict;
import com.common.entity.user.CrSysDictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrSysDictMapper {
    long countByExample(CrSysDictExample example);

    int deleteByExample(CrSysDictExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrSysDict record);

    int insertSelective(CrSysDict record);

    List<CrSysDict> selectByExample(CrSysDictExample example);

    CrSysDict selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrSysDict record, @Param("example") CrSysDictExample example);

    int updateByExample(@Param("record") CrSysDict record, @Param("example") CrSysDictExample example);

    int updateByPrimaryKeySelective(CrSysDict record);

    int updateByPrimaryKey(CrSysDict record);
}