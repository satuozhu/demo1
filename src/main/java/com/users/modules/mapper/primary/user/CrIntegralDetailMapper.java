package com.users.modules.mapper.primary.user;

import com.common.entity.user.CrIntegralDetail;
import com.common.entity.user.CrIntegralDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrIntegralDetailMapper {
    long countByExample(CrIntegralDetailExample example);

    int deleteByExample(CrIntegralDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrIntegralDetail record);

    int insertSelective(CrIntegralDetail record);

    List<CrIntegralDetail> selectByExampleWithBLOBs(CrIntegralDetailExample example);

    List<CrIntegralDetail> selectByExample(CrIntegralDetailExample example);

    CrIntegralDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrIntegralDetail record, @Param("example") CrIntegralDetailExample example);

    int updateByExampleWithBLOBs(@Param("record") CrIntegralDetail record, @Param("example") CrIntegralDetailExample example);

    int updateByExample(@Param("record") CrIntegralDetail record, @Param("example") CrIntegralDetailExample example);

    int updateByPrimaryKeySelective(CrIntegralDetail record);

    int updateByPrimaryKeyWithBLOBs(CrIntegralDetail record);

    int updateByPrimaryKey(CrIntegralDetail record);
}