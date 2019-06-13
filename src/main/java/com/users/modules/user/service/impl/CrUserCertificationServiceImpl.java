package com.users.modules.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.users.component.entity.Message;
import com.users.component.entity.STATUS;
import com.users.component.util.IDCardOCRUtil;
import com.users.component.util.IdGenerator;
import com.users.modules.user.entity.CrUser;
import com.common.entity.user.CrUserCertification;
import com.common.entity.user.CrUserCertificationExample;
import com.common.entity.user.CrUserCertificationWithBLOBs;
import com.users.modules.mapper.primary.user.CrUserCertificationMapper;
import com.users.modules.mapper.primary.user.CrUserMapper;
import com.users.modules.user.requestBody.crAppointment.AddCrIntegralDetailReqBody;
import com.users.modules.user.requestBody.crUserCertification.AddUserCertificationReqBody;
import com.users.modules.user.requestBody.crUserCertification.AuditUserCertificationReqBody;
import com.users.modules.user.requestBody.crUserCertification.UpdateUserCertificationReqBody;
import com.users.modules.user.service.AppointmentService;
import com.users.modules.user.service.CrUserCertificationService;
import com.users.modules.user.service.ICrIntegralService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 身份验证service接口实现
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "Cache", keyGenerator = "myKeyGenerator")
public class CrUserCertificationServiceImpl implements CrUserCertificationService {

    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private CrUserCertificationMapper crUserCertificationMapper;
    @Autowired
    private CrUserMapper crUserMapper;
    @Autowired
    private ICrIntegralService iCrIntegralService;

    /**
     * 添加身份验证信息
     *
     * @param reqBody
     * @return
     */
    @Override
    public Message<?> addUserCertification(AddUserCertificationReqBody reqBody) {
        try {
            CrUserCertificationExample example = new CrUserCertificationExample();
            example.createCriteria().andUserIdEqualTo(reqBody.getUserId());
//            int num = crUserCertificationMapper.deleteByExample(example);
//            log.info("删除历史认证数据num=" + num);
            List<CrUserCertification> list = crUserCertificationMapper.selectByExample(example);
            if(list.size() > 0){
                return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, "身份验证信息已存在");
            }

            long now = System.currentTimeMillis();
            CrUserCertificationWithBLOBs cuc = new CrUserCertificationWithBLOBs();
            cuc.setId(idGenerator.nextId());
            cuc.setUserId(reqBody.getUserId());
//            cuc.setRealName("");
//            cuc.setIdCardNo("");
//            cuc.setSex("1");//性别(0:女 1:男)
//            cuc.setBirthday("");
//            cuc.setNation("");
//            cuc.setBirthAddress("");
//            cuc.setIssueAddress("");
//            cuc.setIssueDate("");
//            cuc.setExpiryDate("");
            cuc.setIdCardFront(reqBody.getIdCardFront());
            cuc.setIdCardBack(reqBody.getIdCardBack());
            cuc.setFaceAuth(reqBody.getFaceAuth());
            cuc.setAuthStatus(1);//认证状态：0未认证、1认证中、2认证通过、3认证失败
//            cuc.setFailReason("");
            cuc.setCreateTime(now);
            cuc.setUpdateTime(now);
            if (crUserCertificationMapper.insertSelective(cuc) > 0) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 查询当前用户的身份验证信息
     *
     * @param userId
     * @return
     */
    @Cacheable
    @Override
    public Message<CrUserCertificationWithBLOBs> selectUserCertificationByUserId(Long userId) {
        try {
            CrUserCertificationExample example = new CrUserCertificationExample();
            example.createCriteria().andUserIdEqualTo(userId);
            List<CrUserCertificationWithBLOBs> list = crUserCertificationMapper.selectByExampleWithBLOBs(example);
            if (null != list && !list.isEmpty()) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, list.get(0));
            }
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 根据身份ID修改身份验证信息
     *
     * @param reqBody
     * @return
     */
    @Override
    public Message<?> updateUserCertification(UpdateUserCertificationReqBody reqBody) {
        try {
            CrUserCertificationWithBLOBs cuc = new CrUserCertificationWithBLOBs();
            cuc.setId(reqBody.getId());
            cuc.setIdCardFront(reqBody.getIdCardFront());
            cuc.setIdCardBack(reqBody.getIdCardBack());
            cuc.setFaceAuth(reqBody.getFaceAuth());
            cuc.setAuthStatus(1);//认证状态：0未认证、1认证中、2认证通过、3认证失败
            cuc.setUpdateTime(System.currentTimeMillis());
            if (crUserCertificationMapper.updateByPrimaryKeySelective(cuc) > 0) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 根据身份ID执行身份验证图片识别
     * @param id
     * @return
     */
    @Override
    public Message<?> ocrUserCertification(Long id) {
        try {
            CrUserCertificationWithBLOBs c = crUserCertificationMapper.selectByPrimaryKey(id);
            if (null == c) {
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, "身份验证信息不存在");
            }

            JSONObject front = IDCardOCRUtil.IDCardOCR(c.getIdCardFront(), "front", 1);//正面
            log.info("front=" + front);
            JSONObject back = IDCardOCRUtil.IDCardOCR(c.getIdCardBack(), "back", 1);//反面
            log.info("back=" + back);
            if (null != front && null != back) {
                CrUserCertificationWithBLOBs cuc = new CrUserCertificationWithBLOBs();
                if ("1".equals(front.get("code"))) {//正面
                    JSONObject json = front.getJSONObject("result");
                    cuc.setRealName(json.getString("name"));// 获取名字
                    cuc.setIdCardNo(json.getString("code"));// 获取身份证号
                    if ("女".equals(json.getString("sex"))) {// 获取性别
                        cuc.setSex("0");//性别(0:女 1:男)
                    } else {
                        cuc.setSex("1");//性别(0:女 1:男)
                    }
                    cuc.setBirthday(json.getString("birthday"));// 获取生日
                    cuc.setNation(json.getString("nation"));// 获取民族
                    cuc.setBirthAddress(json.getString("address"));// 获取地址
                }
                if ("1".equals(back.get("code"))) {//反面
                    JSONObject json = back.getJSONObject("result");
                    cuc.setIssueAddress(json.getString("issue"));// 签发机关
                    cuc.setIssueDate(json.getString("issueDate"));// 签发日期
                    cuc.setExpiryDate(json.getString("expiryDate"));// 失效日期
                }
                cuc.setId(id);
                cuc.setUpdateTime(System.currentTimeMillis());
                if (crUserCertificationMapper.updateByPrimaryKeySelective(cuc) > 0) {
                    return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
                }
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 根据身份ID执行身份验证信息审核
     *
     * @param reqBody
     * @return
     */
    @Override
    public Message<?> auditUserCertification(AuditUserCertificationReqBody reqBody) {
        try {
            long now = System.currentTimeMillis();
            CrUserCertificationWithBLOBs cuc = new CrUserCertificationWithBLOBs();
            cuc.setId(reqBody.getId());
            cuc.setAuthStatus(reqBody.getAuthStatus());//认证状态：0未认证、1认证中、2认证通过、3认证失败
            cuc.setFailReason(reqBody.getFailReason());//认证失败原因
            cuc.setUpdateTime(now);
            if (crUserCertificationMapper.updateByPrimaryKeySelective(cuc) > 0) {
                if(reqBody.getAuthStatus() == 2){//2认证通过，则更新用户状态为已认证，用户头像加"认证"图标
                    CrUserCertificationWithBLOBs cucw = crUserCertificationMapper.selectByPrimaryKey(reqBody.getId());
                    CrUser crUser = new CrUser();
                    crUser.setId(cucw.getUserId());
                    crUser.setAuthType(1);//认证类型(0未认证、1已认证)
                    crUser.setUpdateTime(now);
                    if(crUserMapper.updateByPrimaryKeySelective(crUser) > 0){
                        //添加积分
                        AddCrIntegralDetailReqBody acidReqBody = new AddCrIntegralDetailReqBody();
                        acidReqBody.setUserId(cucw.getUserId());
                        acidReqBody.setUserName(cucw.getRealName());
                        acidReqBody.setType("1007");
                        acidReqBody.setSourceObject("用户实名认证");
                        iCrIntegralService.addCrIntegralDetail(acidReqBody);
                    }
                }
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

}

