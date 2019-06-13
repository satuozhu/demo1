package com.users.modules.mapper.primary.role;

import com.common.entity.role.CrSysModule;
import com.common.entity.role.CrSysModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrSysModuleDAO {
    long countByExample(CrSysModuleExample example);

    int deleteByExample(CrSysModuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrSysModule record);

    int insertSelective(CrSysModule record);

    List<CrSysModule> selectByExample(CrSysModuleExample example);

    CrSysModule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrSysModule record, @Param("example") CrSysModuleExample example);

    int updateByExample(@Param("record") CrSysModule record, @Param("example") CrSysModuleExample example);

    int updateByPrimaryKeySelective(CrSysModule record);

    int updateByPrimaryKey(CrSysModule record);
}