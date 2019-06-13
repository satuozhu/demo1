package com.users.modules.user.service;

import com.common.entity.user.*;
import com.users.component.entity.Message;
import com.users.component.entity.MyPageInfo;
import com.users.modules.user.requestBody.crAppointment.*;
import com.users.modules.user.responseBody.crAppointment.*;

import java.util.List;

/**
 * 积分service接口
 */
public interface ICrIntegralService {

    /**
     * 添加积分记录
     *
     * @param reqBody
     * @return
     */
    Message<?> addCrIntegralDetail(AddCrIntegralDetailReqBody reqBody);

    /**
     * 分页查询最近30天的积分明细
     *
     * @param reqBody
     * @return
     */
    Message<MyPageInfo<CrIntegralDetailResBody>> selectCrIntegralDetailList(CrIntegralDetailListReqBody reqBody);

    /**
     * 根据用户ID查询用户的积分
     *
     * @param userId
     * @return
     */
    Message<CrIntegral> selectCrIntegralByUserId(Long userId);

}

