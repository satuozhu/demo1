package com.users.modules.mapper.primary.user;

import com.common.entity.user.CrSysMessage;
import com.common.entity.user.CrSysMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrSysMessageDAO {
    long countByExample(CrSysMessageExample example);

    int deleteByExample(CrSysMessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrSysMessage record);

    int insertSelective(CrSysMessage record);

    List<CrSysMessage> selectByExample(CrSysMessageExample example);

    CrSysMessage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrSysMessage record, @Param("example") CrSysMessageExample example);

    int updateByExample(@Param("record") CrSysMessage record, @Param("example") CrSysMessageExample example);

    int updateByPrimaryKeySelective(CrSysMessage record);

    int updateByPrimaryKey(CrSysMessage record);
}