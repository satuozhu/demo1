package com.users.modules.mapper.primary.user;

import com.common.entity.user.CrDoctorComment;
import com.common.entity.user.CrDoctorCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrDoctorCommentDAO {
    long countByExample(CrDoctorCommentExample example);

    int deleteByExample(CrDoctorCommentExample example);

    int insert(CrDoctorComment record);

    int insertSelective(CrDoctorComment record);

    List<CrDoctorComment> selectByExample(CrDoctorCommentExample example);

    int updateByExampleSelective(@Param("record") CrDoctorComment record, @Param("example") CrDoctorCommentExample example);

    int updateByExample(@Param("record") CrDoctorComment record, @Param("example") CrDoctorCommentExample example);
}