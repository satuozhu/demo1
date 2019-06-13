package com.users.modules.user.service;

import com.common.entity.user.CrCase;

import java.util.List;

/**
 * Created by xiangchao on 2019/5/27.
 */
public interface ICrCaseService {

    /**
     * 根据医生编号查询医生案例
     */
    List<CrCase> getCaseByDoctorId(Long id);
}
