package com.users.modules.mapper.primary.user;

import java.util.List;

import com.common.entity.user.CrDoctorInfo;
import com.common.entity.user.CrDoctorInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrDoctorInfoMapper {
    long countByExample(CrDoctorInfoExample example);

    int deleteByExample(CrDoctorInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrDoctorInfo record);

    int insertSelective(CrDoctorInfo record);

    List<CrDoctorInfo> selectByExample(CrDoctorInfoExample example);

    CrDoctorInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrDoctorInfo record, @Param("example") CrDoctorInfoExample example);

    int updateByExample(@Param("record") CrDoctorInfo record, @Param("example") CrDoctorInfoExample example);

    int updateByPrimaryKeySelective(CrDoctorInfo record);

    int updateByPrimaryKey(CrDoctorInfo record);
}