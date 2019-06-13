package com.users.modules.pres.service;

import com.common.entity.pres.CrPrescription;

import java.util.List;

/**
 * Created by xiangchao on 2019/5/24.
 */
public interface ICrPrescriptionService {

    /**
     * 根据患者编号查询所有处方信息
     */
    List<CrPrescription> getUserPres(Long userId);

    /**
     * 根据处方编号,查询处方详细信息
     */
    CrPrescription getPresDetails(Long presId);
}
