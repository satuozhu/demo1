package com.users.modules.mapper.primary.user;

import com.common.entity.user.CrQuestion;
import com.common.entity.user.CrQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrQuestionDAO {
    long countByExample(CrQuestionExample example);

    int deleteByExample(CrQuestionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrQuestion record);

    int insertSelective(CrQuestion record);

    List<CrQuestion> selectByExample(CrQuestionExample example);

    CrQuestion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrQuestion record, @Param("example") CrQuestionExample example);

    int updateByExample(@Param("record") CrQuestion record, @Param("example") CrQuestionExample example);

    int updateByPrimaryKeySelective(CrQuestion record);

    int updateByPrimaryKey(CrQuestion record);
}