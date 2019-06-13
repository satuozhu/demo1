package com.users.modules.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.api.framework.util.SMSServiceUtil;
import com.common.entity.user.CrIntegral;
import com.common.entity.user.CrIntegralExample;
import com.users.component.config.aspect.annotation.LogForController;
import com.users.component.entity.Message;
import com.users.component.entity.STATUS;
import com.users.component.util.CharacterUtils;
import com.users.component.util.CheckUtil;
import com.users.component.util.JwtTokenUtils;
import com.users.component.util.RedisUtils;
import com.users.modules.mapper.primary.user.CrIntegralMapper;
import com.users.modules.mapper.primary.user.CrUserMapper;
import com.users.modules.user.entity.CrUser;
import com.users.modules.user.requestBody.login.*;
import com.users.modules.user.responseBody.login.LoginResBody;
import com.users.modules.user.service.CrUserService;
import com.users.modules.user.service.LoginService;
import com.users.modules.user.service.RegisteredService;
import com.users.modules.user.service.impl.UserDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 登录、注销控制器
 */
@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "*")
@Api(description = "登录接口")
public class LoginController {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private SMSServiceUtil smsServiceUtil;
    @Autowired
    private CharacterUtils characterUtils;
    @Autowired
    private RegisteredService registeredService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private CrUserService crUserService;
    @Autowired
    private CrUserMapper crUserDAO;
    @Autowired
    private CrIntegralMapper crIntegralMapper;

    //@ApiIgnore // 屏蔽（内部使用，不对外）
    @LogForController
    @ApiOperation(value = "密码登录(账号、手机号、邮箱)")
    //@ApiOperation(value = "密码登录(账号、手机号、邮箱)1", notes = "密码登录(账号、手机号、邮箱)2")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Message<LoginResBody> login(@ApiParam(value = "必填参数：userAccount、pwd,其他字段选填", required = true) @RequestBody LoginReqBody reqBody) {
        log.info("密码登录(账号、手机号、邮箱)");
        if (StringUtils.isBlank(reqBody.getUserAccount())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getPwd())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == reqBody.getPlatformType() || reqBody.getPlatformType() <= 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == reqBody.getLoginType() || reqBody.getLoginType() <= 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }

        CrUser crUser = (CrUser) userDetailService.loadUserByUsername(reqBody.getUserAccount());
        if (crUser == null) {
            //throw new BusinessException(STATUS.JWT_ERROR, "用户账号不存在");
            return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_NO_USER_ACCOUNT);
        }
        // 获取前台加密后的密码
        String pwd = reqBody.getPwd();
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(reqBody.getUserAccount(), pwd + crUser.getSalt());
        try {
            Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            //throw new BusinessException(STATUS.ERROR, "密码错误");
            return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_PASSWORD_ERROR);
        }

        crUser.setPlatformType(reqBody.getPlatformType());//平台类型（1移动端、2PC端）
        crUser.setLoginType(reqBody.getLoginType());//登录类型（1账号、2微信、3手机、4邮箱）
        crUserDAO.updateByPrimaryKeySelective(crUser);

        LoginResBody resBody = new LoginResBody();
        resBody.setToken(jwtTokenUtils.generateToken(crUser));
        crUser.setPwd("");
        crUser.setSalt("");
        resBody.setUser(crUser);
        resBody.setBindMobile((null != crUser.getPhone() && !"".equals(crUser.getPhone())) ? 1 : 0);
        resBody.setBindWechat((null != crUser.getOpenid() && !"".equals(crUser.getOpenid())) ? 1 : 0);
        CrIntegralExample example = new CrIntegralExample();
        example.createCriteria().andUserIdEqualTo(crUser.getId());
        List<CrIntegral> list = crIntegralMapper.selectByExample(example);
        resBody.setTotalIntegral(list.size() > 0 ? list.get(0).getTotleScore() : 0);
        resBody.setTotalCollect(0);
        resBody.setTotalFollow(0);

//        JSONObject json = new JSONObject();
//        json.put("token", jwtTokenUtils.generateToken(crUser));
//        crUser.setPwd("");
//        crUser.setSalt("");
//        json.put("user", crUser);
//        json.put("bindMobile", (null != crUser.getPhone() && !"".equals(crUser.getPhone())) ? 1 : 0);
//        json.put("bindWechat", (null != crUser.getOpenid() && !"".equals(crUser.getOpenid())) ? 1 : 0);
//        CrIntegralExample example = new CrIntegralExample();
//        example.createCriteria().andUserIdEqualTo(crUser.getId());
//        List<CrIntegral> list = crIntegralMapper.selectByExample(example);
//        json.put("totalIntegral", list.size() > 0 ? list.get(0).getTotleScore() : 0);
//        json.put("totalCollect", 0);
//        json.put("totalFollow", 0);
        return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, resBody);
    }

    @LogForController
    @ApiOperation(value = "注销")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Message<?> logout(@ApiParam(value = "token", required = true) @RequestParam String token) {
        log.info("注销");
        if (StringUtils.isBlank(token)) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, jwtTokenUtils.logoutTokens(token));
    }

    @LogForController
    @ApiOperation(value = "验证码登录(手机号、邮箱)")
    @RequestMapping(value = "/loginCode", method = RequestMethod.POST)
    public Message<LoginResBody> registered(@ApiParam(value = "必填参数：userAccount、type、code,其他字段选填", required = true) @RequestBody LoginCodeReqBody reqBody) {
        log.info("验证码登录(手机号、邮箱)");
        if (StringUtils.isBlank(reqBody.getUserAccount())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == reqBody.getType() || reqBody.getType() <= 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getCode())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == reqBody.getPlatformType() || reqBody.getPlatformType() <= 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == reqBody.getLoginType() || reqBody.getLoginType() <= 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }

        try {
            //校验验证码
            String redCode = null;
            if (reqBody.getType() == 3) {//3手机验证码登录
                redCode = (String) redisUtils.get("verifCode:" + reqBody.getUserAccount() + smsServiceUtil.PHONE_LOGIN_VERIFCODE);
            }
            if (reqBody.getType() == 13) {//13邮箱验证码登录
                redCode = (String) redisUtils.get("verifCode:" + reqBody.getUserAccount() + smsServiceUtil.EMAIL_LOGIN_CODE);
            }
            if (!reqBody.getCode().equalsIgnoreCase(redCode)) {//redCode == null || equalsIgnoreCase忽略大小写
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
            if (crUser == null) {
                //return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_NO_USER_ACCOUNT);
                //注册
                //characterUtils.RandomChaeacter(6)
                crUser = registeredService.registered(reqBody.getUserAccount(), "123456");
                if (null == crUser) {
                    return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR);
                }
            }

//            // 获取后台加密后的密码
//            String pwd = cu.getPwd();
//            pwd = pwd;//密码解密
//            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(crUserReg.getUserAccount(), pwd);
//            try {
//                Authentication authentication = authenticationManager.authenticate(upToken);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } catch (Exception e) {
//                //throw new BusinessException(STATUS.ERROR, "密码错误");
//                return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_PASSWORD_ERROR);
//            }

//            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(auth);

            crUser.setPlatformType(reqBody.getPlatformType());//平台类型（1移动端、2PC端）
            crUser.setLoginType(reqBody.getLoginType());//登录类型（1账号、2微信、3手机、4邮箱）
            crUserDAO.updateByPrimaryKeySelective(crUser);

            LoginResBody resBody = new LoginResBody();
            resBody.setToken(jwtTokenUtils.generateToken(crUser));
            crUser.setPwd("");
            crUser.setSalt("");
            resBody.setUser(crUser);
            resBody.setBindMobile((null != crUser.getPhone() && !"".equals(crUser.getPhone())) ? 1 : 0);
            resBody.setBindWechat((null != crUser.getOpenid() && !"".equals(crUser.getOpenid())) ? 1 : 0);
            CrIntegralExample example = new CrIntegralExample();
            example.createCriteria().andUserIdEqualTo(crUser.getId());
            List<CrIntegral> list = crIntegralMapper.selectByExample(example);
            resBody.setTotalIntegral(list.size() > 0 ? list.get(0).getTotleScore() : 0);
            resBody.setTotalCollect(0);
            resBody.setTotalFollow(0);

//            JSONObject json = new JSONObject();
//            json.put("token", jwtTokenUtils.generateToken(crUser));
//            crUser.setPwd("");
//            crUser.setSalt("");
//            json.put("user", crUser);
//            json.put("bindMobile", (null != crUser.getPhone() && !"".equals(crUser.getPhone())) ? 1 : 0);
//            json.put("bindWechat", (null != crUser.getOpenid() && !"".equals(crUser.getOpenid())) ? 1 : 0);
//            CrIntegralExample example = new CrIntegralExample();
//            example.createCriteria().andUserIdEqualTo(crUser.getId());
//            List<CrIntegral> list = crIntegralMapper.selectByExample(example);
//            json.put("totalIntegral", list.size() > 0 ? list.get(0).getTotleScore() : 0);
//            json.put("totalCollect", 0);
//            json.put("totalFollow", 0);
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, resBody);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    @LogForController
    @ApiOperation(value = "用户绑定(手机号、邮箱)")
    @RequestMapping(value = "/bindUserAccount", method = RequestMethod.POST)
    public Message<?> bindUserAccount(@ApiParam(value = "必填参数：userId、userAccount、type=4、code,其他字段选填", required = true) @RequestBody BindUserAccountReqBody reqBody) {
        log.info("用户绑定(手机号、邮箱)");
        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getUserAccount())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == reqBody.getType() || reqBody.getType() <= 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getCode())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return loginService.bindUserAccount(reqBody);
    }

    @ApiOperation(value = "用户解除绑定(手机号、邮箱)")
    @RequestMapping(value = "/unbindUserAccount", method = RequestMethod.POST)
    public Message<?> unbindUserAccount(@ApiParam(value = "必填参数：userId、userAccount、type=4、code,其他字段选填", required = true) @RequestBody UnbindUserAccountReqBody reqBody) {
        log.info("用户解除绑定(手机号、邮箱)");
        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getUserAccount())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == reqBody.getType() || reqBody.getType() <= 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getCode())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return loginService.unbindUserAccount(reqBody);
    }

    @LogForController
    @ApiOperation(value = "找回密码(手机号、邮箱)")
    @RequestMapping(value = "/retrievePassword", method = RequestMethod.POST)
    public Message<?> retrievePassword(@ApiParam(value = "必填参数：userAccount、type、code、pwd、repwd,其他字段选填", required = true) @RequestBody RetrievePasswordReqBody reqBody) {
        log.info("找回密码(手机号、邮箱)");
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
            if (reqBody.getType() == 2) {//2手机重置密码
                redCode = (String) redisUtils.get("verifCode:" + reqBody.getUserAccount() + smsServiceUtil.PHONE_RESTPASSWORD_VERIFCODE);
            }
            if (reqBody.getType() == 12) {//12邮箱重置密码
                redCode = (String) redisUtils.get("verifCode:" + reqBody.getUserAccount() + smsServiceUtil.EMAIL_RESTPASSWORD_CODE);
            }
            if (!reqBody.getCode().equalsIgnoreCase(redCode)) {//null == redCode || equalsIgnoreCase忽略大小写
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
            if (crUser == null) {
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_NO_USER_ACCOUNT);
            }

            //设置新密码
            crUser.setPwd(DigestUtils.md5DigestAsHex((reqBody.getPwd() + crUser.getSalt()).getBytes()));
            int num = loginService.retrievePassword(crUser);
            if (num > 0) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

//    public static void main(String[] args) {
//        String password = "123456";
//
//        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
//        //传入明文密码，返回加密后密码
//        String jiami1 = bCrypt.encode(password);//每一次都不一样
//        System.out.println("加密后的密码jiami1 = " + jiami1);
//        String pwd1 = "$2a$10$YgdlU7vzIQZrTElowv9nx.80GQRpkQUjLNkseKR1k9v5TzleN9pQG";
//        //k1是明文密码，k2为加密过的密码，返回true或false
//        System.out.println("bCrypt密码对比=" + bCrypt.matches(password, jiami1));
//
//        Pbkdf2PasswordEncoder pbkdf2 = new Pbkdf2PasswordEncoder();
//        String jiami2 = pbkdf2.encode(password);//每一次都不一样
//        System.out.println("加密后的密码jiami2=" + jiami2);
//        String pwd2 = "b70230f96133380427343668cfa6c40bc1844bc0f484b5df95151b5e6b33634c9ad50709d3f3cfc1";
//        System.out.println("pbkdf2密码对比=" + pbkdf2.matches(password, jiami2));
//
//        SCryptPasswordEncoder sCrypt = new SCryptPasswordEncoder();
//        String jiami3 = sCrypt.encode(password);//每一次都不一样
//        System.out.println("加密后的密码jiami3=" + jiami3);
//        String pwd3 = "$e0801$hci7sm/a2J278FFWAiF86LIY7jPu23iTpSRvmPRG0aW94B7QQBIKiFca+TEuvX0pwNnlCmSS0W7HbbNBE4CnLQ==$NOw3PCWLfIrVRAbEXDFXSjULADrsvlHkT4rnNexYLk0=";
//        System.out.println("sCrypt密码对比=" + sCrypt.matches(password, jiami3));
//
//        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        String jiami4 = passwordEncoder.encode(password);//每一次都不一样
//        System.out.println("加密后的密码jiami4=" + jiami4);
//        String pwd4 = "{bcrypt}$2a$10$b/DkAbHyQmt6WHgdnh0ZWOAoi1KqJ/2cOmvZUQPdny4uK.c3r4AMG";
//        System.out.println("bcrypt密码对比=" + passwordEncoder.matches(password, jiami4));
//
//        String jiami5 = encode(password);//每一次都不一样
//        System.out.println("加密后的密码jiami5=" + jiami5);
//        String pwd5 = "e10adc3949ba59abbe56e057f20f883e";
//        System.out.println("MD5密码对比=" + passwordEncoder.matches(password, jiami4));
//
////        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
////        System.out.println(encoder.encodePassword("123456", "hongxf"));
//
//    }
//
//    /**
//     * MD5加密
//     * @param charSequence
//     * @return
//     */
//    public static String encode(CharSequence charSequence) {
//        return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
//    }
//
//    /**
//     * @param charSequence 明文
//     * @param s 密文
//     * @return
//     */
//    public static boolean matches(CharSequence charSequence, String s) {
//        return s.equals(DigestUtils.md5DigestAsHex(charSequence.toString().getBytes()));
//    }

}
