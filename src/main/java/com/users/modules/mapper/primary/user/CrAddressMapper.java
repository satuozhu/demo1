package com.users.modules.mapper.primary.user;

import com.common.entity.user.CrAddress;
import com.common.entity.user.CrAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrAddressMapper {
    long countByExample(CrAddressExample example);

    int deleteByExample(CrAddressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrAddress record);

    int insertSelective(CrAddress record);

    List<CrAddress> selectByExample(CrAddressExample example);

    CrAddress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrAddress record, @Param("example") CrAddressExample example);

    int updateByExample(@Param("record") CrAddress record, @Param("example") CrAddressExample example);

    int updateByPrimaryKeySelective(CrAddress record);

    int updateByPrimaryKey(CrAddress record);
}