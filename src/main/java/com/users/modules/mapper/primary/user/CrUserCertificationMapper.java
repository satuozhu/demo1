package com.users.modules.mapper.primary.user;

import com.common.entity.user.CrUserCertification;
import com.common.entity.user.CrUserCertificationExample;
import com.common.entity.user.CrUserCertificationWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrUserCertificationMapper {
    long countByExample(CrUserCertificationExample example);

    int deleteByExample(CrUserCertificationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrUserCertificationWithBLOBs record);

    int insertSelective(CrUserCertificationWithBLOBs record);

    List<CrUserCertificationWithBLOBs> selectByExampleWithBLOBs(CrUserCertificationExample example);

    List<CrUserCertification> selectByExample(CrUserCertificationExample example);

    CrUserCertificationWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrUserCertificationWithBLOBs record, @Param("example") CrUserCertificationExample example);

    int updateByExampleWithBLOBs(@Param("record") CrUserCertificationWithBLOBs record, @Param("example") CrUserCertificationExample example);

    int updateByExample(@Param("record") CrUserCertification record, @Param("example") CrUserCertificationExample example);

    int updateByPrimaryKeySelective(CrUserCertificationWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CrUserCertificationWithBLOBs record);

    int updateByPrimaryKey(CrUserCertification record);
}