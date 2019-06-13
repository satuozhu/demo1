package com.users.modules.user.controller;

import com.api.framework.util.EmailManager;
import com.api.framework.util.SMSServiceUtil;
import com.users.component.config.aspect.annotation.LogForController;
import com.users.component.entity.Message;
import com.users.component.entity.STATUS;
import com.users.component.util.CharacterUtils;
import com.users.component.util.CheckUtil;
import com.users.component.util.RedisUtils;
import com.users.modules.user.entity.CrUser;
import com.users.modules.user.requestBody.registered.RegisteredAccountReqBody;
import com.users.modules.user.requestBody.registered.RegisteredReqBody;
import com.users.modules.user.service.CrUserService;
import com.users.modules.user.service.RegisteredService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 注册控制器
 */
@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "*")
@Api(description = "注册接口")
public class RegisteredController {

    @Autowired
    private CharacterUtils characterUtils;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private SMSServiceUtil smsServiceUtil;
    @Autowired
    private EmailManager emailManager;
    @Autowired
    private RegisteredService registeredService;
    @Autowired
    private CrUserService crUserService;

    @LogForController
    @ApiOperation(value = "发送验证码(手机号、邮箱)")
    @RequestMapping(value = "/sendVerifCode", method = RequestMethod.GET)
    public Message<?> sendVerifCode(
            @ApiParam(value = "国家编码", required = true, defaultValue = "86") @RequestParam("countryCode") String countryCode,
            @ApiParam(value = "手机号或邮箱", required = true) @RequestParam String userAccount,
            @ApiParam(value = "验证码类型：1手机注册2手机重置密码3手机验证码登录4手机验证码绑定；11邮箱注册12邮箱重置密码13邮箱验证码登录14邮箱验证码绑定", required = true) @RequestParam Integer type) {
        Boolean isSend = false;//验证码发送结果
        String key = "";//验证码类型
        //生成验证码
        String code = characterUtils.getRandomChaeacter(6);
        log.info("发送验证码(手机号、邮箱)code="+code);

        if (StringUtils.isBlank(countryCode)) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(userAccount)) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == type || type <= 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }

        try {
            if (CheckUtil.isMobile(userAccount)) {//手机
                // 调阿里云短信验证
                if (type == 1) {//1手机注册
                    key = smsServiceUtil.PHONE_REGISTER_VERIFCODE;
                }
                if (type == 2) {//2手机重置密码
                    key = smsServiceUtil.PHONE_RESTPASSWORD_VERIFCODE;
                }
                if (type == 3) {//3手机验证码登录
                    key = smsServiceUtil.PHONE_LOGIN_VERIFCODE;
                }
                if (type == 4) {//4手机验证码绑定
                    key = smsServiceUtil.PHONE_BIND_VERIFCODE;
                }
                isSend = true;
//                isSend = smsServiceUtil.sendSMS(countryCode, userAccount, code, type);
            } else if (CheckUtil.isEmail(userAccount)) {//邮箱
                if (type == 11) {//11邮箱注册
                    key = smsServiceUtil.EMAIL_REGISTER_CODE;
                }
                if (type == 12) {//12邮箱重置密码
                    key = smsServiceUtil.EMAIL_RESTPASSWORD_CODE;
                }
                if (type == 13) {//13邮箱验证码登录
                    key = smsServiceUtil.EMAIL_LOGIN_CODE;
                }
                if (type == 14) {//13邮箱验证码绑定
                    key = smsServiceUtil.EMAIL_LOGIN_CODE;
                }
//                isSend = true;
                isSend = emailManager.sendMail(userAccount, code, type);
            }
            log.info("isSend=" + isSend);
            if (isSend) {
                // redis有效时间为15分钟
                redisUtils.set("verifCode:" + userAccount + key, code, 15 * 60L);
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SEND_SUCCESS, code);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_SEND_ERR);
        } catch (Exception e) {
            //throw new BusinessException(STATUS.ERROR, "发送失败");
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    @LogForController
    @ApiOperation(value = "注册(手机号、邮箱)")
    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    public Message<?> registered(@ApiParam(value = "必填参数：userAccount、type、code、pwd、repwd,其他字段选填", required = true) @RequestBody RegisteredReqBody reqBody) {
        log.info("注册(手机号、邮箱)");
        if (StringUtils.isBlank(reqBody.getUserAccount())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == reqBody.getType() || reqBody.getType() <= 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getCode())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getPwd())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getRepwd())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }

        try {
            //校验密码
            if (!reqBody.getPwd().equals(reqBody.getRepwd())) {
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_PASSWORD_REPWD_NO);
            }

            //校验验证码
            String redCode = null;
            if (reqBody.getType() == 1) {//1手机注册
                redCode = (String) redisUtils.get("verifCode:" + reqBody.getUserAccount() + smsServiceUtil.PHONE_REGISTER_VERIFCODE);
            }
            if (reqBody.getType() == 11) {//11邮箱注册
                redCode = (String) redisUtils.get("verifCode:" + reqBody.getUserAccount() + smsServiceUtil.EMAIL_REGISTER_CODE);
            }
            if (redCode == null || !reqBody.getCode().equalsIgnoreCase(redCode)) {//equalsIgnoreCase忽略大小写
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_CODE_ERROR);
            }

            //校验账号
            CrUser crUser = null;
            if (CheckUtil.isMobile(reqBody.getUserAccount())) {//是手机号
                crUser = crUserService.findCrUserByPhone(reqBody.getUserAccount());
            } else if (CheckUtil.isEmail(reqBody.getUserAccount())) {//是邮箱
                crUser = crUserService.findCrUserByEmail(reqBody.getUserAccount());
            } else {
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_USER_ACCOUNT_ERROR);
            }
            if (crUser != null) {
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_YES_USER_ACCOUNT);
            }

            crUser = registeredService.registered(reqBody.getUserAccount(), reqBody.getPwd());
            if (null != crUser) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            //throw new BusinessException(STATUS.ERROR, "注册失败");
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    @LogForController
    @ApiOperation(value = "注册(账号)")
    @RequestMapping(value = "/registeredAccount", method = RequestMethod.POST)
    public Message<?> registeredAccount(@ApiParam(value = "必填参数：userAccount、pwd、repwd,其他字段选填", required = true) @RequestBody RegisteredAccountReqBody reqBody) {
        log.info("注册(账号)");
        if (StringUtils.isBlank(reqBody.getUserAccount())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getPwd())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getRepwd())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }

        try {
            //校验密码
            if (!reqBody.getPwd().equals(reqBody.getRepwd())) {
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_PASSWORD_REPWD_NO);
            }
            //校验账号
            if (!CheckUtil.isLetterDigitOne(reqBody.getUserAccount())) {//6-20位之间的字母、数字、下划线组合
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_USER_ACCOUNT_REGEX_ERROR);
            }
            CrUser crUser = crUserService.findCrUserByAccount(reqBody.getUserAccount());
            if (crUser != null) {
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_YES_USER_ACCOUNT);
            }

            crUser = registeredService.registeredAccount(reqBody);
            if (null != crUser) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            //throw new BusinessException(STATUS.ERROR, "注册失败");
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

}
