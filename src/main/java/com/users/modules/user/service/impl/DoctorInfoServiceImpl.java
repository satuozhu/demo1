package com.users.modules.user.service.impl;

import com.common.entity.user.CrDoctorInfo;
import com.common.entity.user.CrDoctorInfoExample;
import com.users.modules.mapper.primary.user.CrDoctorInfoMapper;
import com.users.modules.user.service.IDoctorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiangchao on 2019/5/27.
 */
@Service
public class DoctorInfoServiceImpl implements IDoctorInfoService {

    @Autowired
    private CrDoctorInfoMapper doctorInfoMapper;

    @Override
    public CrDoctorInfo getDoctorDetail(Long doctorId) {
        CrDoctorInfoExample example = new CrDoctorInfoExample();
        example.createCriteria().andDoctorIdEqualTo(doctorId);
        return doctorInfoMapper.selectByExample(example).get(0);
    }
}
