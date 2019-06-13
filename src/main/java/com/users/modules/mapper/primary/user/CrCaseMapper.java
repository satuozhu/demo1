package com.users.modules.mapper.primary.user;

import com.common.entity.user.CrCase;
import com.common.entity.user.CrCaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrCaseMapper {
    long countByExample(CrCaseExample example);

    int deleteByExample(CrCaseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrCase record);

    int insertSelective(CrCase record);

    List<CrCase> selectByExample(CrCaseExample example);

    CrCase selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrCase record, @Param("example") CrCaseExample example);

    int updateByExample(@Param("record") CrCase record, @Param("example") CrCaseExample example);

    int updateByPrimaryKeySelective(CrCase record);

    int updateByPrimaryKey(CrCase record);
}