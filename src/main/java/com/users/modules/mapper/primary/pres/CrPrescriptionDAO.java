package com.users.modules.mapper.primary.pres;

import com.common.entity.pres.CrPrescription;
import com.common.entity.pres.CrPrescriptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrPrescriptionDAO {
    long countByExample(CrPrescriptionExample example);

    int deleteByExample(CrPrescriptionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrPrescription record);

    int insertSelective(CrPrescription record);

    List<CrPrescription> selectByExample(CrPrescriptionExample example);

    CrPrescription selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrPrescription record, @Param("example") CrPrescriptionExample example);

    int updateByExample(@Param("record") CrPrescription record, @Param("example") CrPrescriptionExample example);

    int updateByPrimaryKeySelective(CrPrescription record);

    int updateByPrimaryKey(CrPrescription record);
}