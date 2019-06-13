package com.users.modules.mapper.primary.user;

import com.common.entity.user.CrFollow;
import com.common.entity.user.CrFollowExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrFollowDAO {
    long countByExample(CrFollowExample example);

    int deleteByExample(CrFollowExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrFollow record);

    int insertSelective(CrFollow record);

    List<CrFollow> selectByExample(CrFollowExample example);

    CrFollow selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrFollow record, @Param("example") CrFollowExample example);

    int updateByExample(@Param("record") CrFollow record, @Param("example") CrFollowExample example);

    int updateByPrimaryKeySelective(CrFollow record);

    int updateByPrimaryKey(CrFollow record);
}