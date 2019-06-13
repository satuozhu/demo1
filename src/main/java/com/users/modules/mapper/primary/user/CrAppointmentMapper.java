package com.users.modules.mapper.primary.user;

import com.common.entity.user.CrAppointment;
import com.common.entity.user.CrAppointmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrAppointmentMapper {
    long countByExample(CrAppointmentExample example);

    int deleteByExample(CrAppointmentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrAppointment record);

    int insertSelective(CrAppointment record);

    List<CrAppointment> selectByExample(CrAppointmentExample example);

    CrAppointment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrAppointment record, @Param("example") CrAppointmentExample example);

    int updateByExample(@Param("record") CrAppointment record, @Param("example") CrAppointmentExample example);

    int updateByPrimaryKeySelective(CrAppointment record);

    int updateByPrimaryKey(CrAppointment record);
}