package com.users.modules.mapper.primary.user;

import com.common.entity.user.CrExpress;
import com.common.entity.user.CrExpressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrExpressMapper {
    long countByExample(CrExpressExample example);

    int deleteByExample(CrExpressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrExpress record);

    int insertSelective(CrExpress record);

    List<CrExpress> selectByExample(CrExpressExample example);

    CrExpress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrExpress record, @Param("example") CrExpressExample example);

    int updateByExample(@Param("record") CrExpress record, @Param("example") CrExpressExample example);

    int updateByPrimaryKeySelective(CrExpress record);

    int updateByPrimaryKey(CrExpress record);
}