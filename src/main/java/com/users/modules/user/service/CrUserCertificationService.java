package com.users.modules.user.service;

import com.common.entity.user.CrUserCertificationWithBLOBs;
import com.users.component.entity.Message;
import com.users.modules.user.requestBody.crUserCertification.AddUserCertificationReqBody;
import com.users.modules.user.requestBody.crUserCertification.AuditUserCertificationReqBody;
import com.users.modules.user.requestBody.crUserCertification.UpdateUserCertificationReqBody;

/**
 * 身份验证service接口
 */
public interface CrUserCertificationService {

    /**
     * 添加身份验证信息
     * @param reqBody
     * @return
     */
    Message<?> addUserCertification(AddUserCertificationReqBody reqBody);

    /**
     * 查询当前用户的身份验证信息
     * @param userId
     * @return
     */
    Message<CrUserCertificationWithBLOBs> selectUserCertificationByUserId(Long userId);

    /**
     * 根据身份ID修改身份验证信息
     * @param reqBody
     * @return
     */
    Message<?> updateUserCertification(UpdateUserCertificationReqBody reqBody);

    /**
     * 根据身份ID执行身份验证图片识别
     * @param id
     * @return
     */
    Message<?> ocrUserCertification(Long id);

    /**
     * 根据身份ID执行身份验证信息审核
     * @param reqBody
     * @return
     */
    Message<?> auditUserCertification(AuditUserCertificationReqBody reqBody);


}

