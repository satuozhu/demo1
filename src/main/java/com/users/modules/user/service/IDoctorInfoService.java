package com.users.modules.user.service;

import com.common.entity.user.CrDoctorInfo;

/**
 * Created by xiangchao on 2019/5/27.
 */
public interface IDoctorInfoService {
    /**
     * 根据医生编号查询医生详情
     */
    CrDoctorInfo getDoctorDetail(Long doctorId);
}
