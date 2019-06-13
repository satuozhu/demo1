package com.users.modules.mapper.primary.user;

import java.util.List;

import com.common.entity.user.CrAppointmentAgain;
import com.common.entity.user.CrAppointmentAgainExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrAppointmentAgainMapper {
    long countByExample(CrAppointmentAgainExample example);

    int deleteByExample(CrAppointmentAgainExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrAppointmentAgain record);

    int insertSelective(CrAppointmentAgain record);

    List<CrAppointmentAgain> selectByExample(CrAppointmentAgainExample example);

    CrAppointmentAgain selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrAppointmentAgain record, @Param("example") CrAppointmentAgainExample example);

    int updateByExample(@Param("record") CrAppointmentAgain record, @Param("example") CrAppointmentAgainExample example);

    int updateByPrimaryKeySelective(CrAppointmentAgain record);

    int updateByPrimaryKey(CrAppointmentAgain record);
}