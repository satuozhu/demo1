package com.users.modules.mapper.primary.user;

import com.common.entity.user.CrAppointmentTime;
import com.common.entity.user.CrAppointmentTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrAppointmentTimeMapper {
    long countByExample(CrAppointmentTimeExample example);

    int deleteByExample(CrAppointmentTimeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrAppointmentTime record);

    int insertSelective(CrAppointmentTime record);

    List<CrAppointmentTime> selectByExample(CrAppointmentTimeExample example);

    CrAppointmentTime selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrAppointmentTime record, @Param("example") CrAppointmentTimeExample example);

    int updateByExample(@Param("record") CrAppointmentTime record, @Param("example") CrAppointmentTimeExample example);

    int updateByPrimaryKeySelective(CrAppointmentTime record);

    int updateByPrimaryKey(CrAppointmentTime record);
}