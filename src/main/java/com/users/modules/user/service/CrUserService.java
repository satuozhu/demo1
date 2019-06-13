package com.users.modules.user.service;

import com.common.entity.user.CrDoctorComment;
import com.users.component.entity.Message;
import com.users.modules.user.entity.CrUser;
import com.users.component.entity.MyPageInfo;
import com.users.modules.user.responseBody.CrUserResBody;


/**
 * 用户service接口
 */
public interface CrUserService {

    /**
     * 根据用户ID查询用户
     *
     * @param userId
     * @return
     */
    Message<CrUserResBody> findCrUserByUserId(Long userId);

    /**
     * 根据账号查找用户信息
     *
     * @param account
     * @return
     */
    CrUser findCrUserByAccount(String account);

    /**
     * 根据手机号查找用户信息
     *
     * @param phone
     * @return
     */
    CrUser findCrUserByPhone(String phone);

    /**
     * 根据邮箱查找用户信息
     *
     * @param email
     * @return
     */
    CrUser findCrUserByEmail(String email);

    /**
     * 根据微信openid查询用户
     *
     * @param openid
     * @return
     */
    CrUser findCrUserByOpenid(String openid);

    /**
     * 查找全部用户信息
     * @param crUser
     * @param pageNum
     * @param pageSize
     * @return
     */
    //MyPageInfo<CrUser> findCrUser(Paging paging);
    Message<MyPageInfo<CrUser>> findCrUser(CrUser crUser, int pageNum, int pageSize);

    /**
     * 更新用户
     *
     * @param crUserReg
     * @return
     */
    Message<?> updateCrUser(CrUser crUserReg);

    /**
     * 根据用户ID删除用户信息
     *
     * @param userId
     * @return
     */
    Message<?> delCrUserByUserId(Long userId);

    /**
     * 【admin在使用】查找全部用户信息
     *
     * @param crUser
     * @param pageNum
     * @param pageSize
     * @return
     */
    Message<MyPageInfo<CrUser>> findCrUserWeb(CrUser crUser, int pageNum, int pageSize);

    /**
     * 【admin在使用】更新用户
     *
     * @param crUserReg
     * @return
     */
    Message<?> updateCrUserWeb(CrUser crUserReg);

    /**
     * 【admin在使用】根据用户ID删除用户信息
     *
     * @param userId
     * @return
     */
    Message<?> delCrUserWebByUserId(Long userId);
}

