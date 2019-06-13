package com.users.modules.mapper.primary.user;

import com.common.entity.user.CrIntegral;
import com.common.entity.user.CrIntegralExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrIntegralMapper {
    long countByExample(CrIntegralExample example);

    int deleteByExample(CrIntegralExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrIntegral record);

    int insertSelective(CrIntegral record);

    List<CrIntegral> selectByExample(CrIntegralExample example);

    CrIntegral selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrIntegral record, @Param("example") CrIntegralExample example);

    int updateByExample(@Param("record") CrIntegral record, @Param("example") CrIntegralExample example);

    int updateByPrimaryKeySelective(CrIntegral record);

    int updateByPrimaryKey(CrIntegral record);
}