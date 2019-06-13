package com.users.modules.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.users.component.config.aspect.annotation.LogForController;
import com.users.component.entity.Message;
import com.users.component.entity.STATUS;
import com.users.component.util.CharacterUtils;
import com.users.component.util.WeixinUtil;
import com.users.modules.user.requestBody.login.LoginWechatAuthReqBody;
import com.users.modules.user.requestBody.wechat.BindWechatReqBody;
import com.users.modules.user.requestBody.wechat.UnbindWechatReqBody;
import com.users.modules.user.service.WechatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信控制器
 */
@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "*")
@Api(description = "微信接口")
public class WechatController {

    @Autowired
    private CharacterUtils characterUtils;
    @Autowired
    private WeixinUtil weixinUtil;
    @Autowired
    private WechatService wechatService;

    /**
     * http://localhost:8003/user/loginWechats
     * http://localhost:8003/views/qrCode.html
     * http://www.szcrain.com:8000/user/swagger-ui.html
     * 【微信网页授权】重定向访问二维码页面
     * @return
     */
    @ApiIgnore // 屏蔽（内部使用，不对外）
    @LogForController
    @RequestMapping(value = "/loginWechats", method = RequestMethod.GET)
    public void logins(HttpServletResponse response) throws IOException {
        //String redirectURL = "http://localhost:8080/views/qrCode.html";
//        response.sendRedirect("/views/qrCode.html");
//        return "redirect:/login?logout";
        UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getDetails();
        System.out.println("username:"+user.getUsername()+";password:"+user.getPassword());
        //anonymousUser
        System.out.println("login-auth:"+SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @ApiIgnore // 屏蔽（内部使用，不对外）
    @LogForController
    @RequestMapping(value="/logouts")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    /**
     * http://localhost/user/wechat/login
     * http://localhost:80/user/wechat/login
     * 【微信网页授权】重定向访问二维码接口
     * @return
     */
    @ApiIgnore // 屏蔽（内部使用，不对外）
    @LogForController
    @RequestMapping(value = "/loginWechat", method = RequestMethod.GET)
    public void login(HttpServletResponse response) throws IOException {
        //String redirectURL = "https://open.weixin.qq.com/connect/qrconnect?appid=wx834b8fe995722007&redirect_uri=https%3A%2F%2Fwww.szcrain.com%2Fuser%2Fcallback&response_type=code&scope=snsapi_login&state=613cdcb56fba6f32f43d0a502c11170a#wechat_redirect";
//        String redirectUri = "https%3A%2F%2Fwww.szcrain.com%3A8000%2Fuser%2Fcallback";
//        String redirectUri = "https://www.szcrain.com:8000/user/callback";
        String redirectUri = "http://www.szcrain.com/user/callback";
        String state = characterUtils.getRandomLowercaseNum(32);
//        System.out.println("state = " + state);
//        redisUtils.set("wechat_qrconnect_state", state);
        String url = weixinUtil.qrconnect(redirectUri,"snsapi_login",state);
        response.sendRedirect(url);
    }

    /**
     * 扫描二维码之后，得到code，并重定向到本接口
     * http://localhost:8003/user/callback?code=081HORrc0ubk3A1Lxitc0FENrc0HORrU&state=613cdcb56fba6f32f43d0a502c11170a
     * http://www.szcrain.com:8000/user/user/callback?code=081HORrc0ubk3A1Lxitc0FENrc0HORrU&state=613cdcb56fba6f32f43d0a502c11170a
     *
     * @param code
     * @param state
     * @throws Exception
     */
    @ApiIgnore // 屏蔽（内部使用，不对外）
    @LogForController
    @RequestMapping(value = "/callback", method = RequestMethod.GET)
//    public Message<> callback(@RequestParam("code") String code, @RequestParam("state") String returnUrl, HttpServletResponse response) throws Exception {
    public Message<?> callback(@RequestParam("code") String code, @RequestParam("state") String state) {
        log.info("扫描二维码之后");

        //数据库添加openid用户信息
        //1、有手机号，数据库查询用户和密码，调登陆接口实现登录
        //2、没有关联手机号，返回没有手机号，前台跳绑定手机号界面
        //（发送手机验证码）调openid绑定手机号接口，成功则调登录接口

        Map<String,Object> map = new HashMap<>();
        LoginWechatAuthReqBody loginAuthReqBody = new LoginWechatAuthReqBody();
//        String stateOld = "";
//        Boolean isTrue = redisUtils.exists("wechat_qrconnect_state");
//        if (isTrue) {
//            stateOld = redisUtils.get("wechat_qrconnect_state").toString();
//        }
//        redisUtils.remove("wechat_qrconnect_state");
//        log.info("stateOld = " + stateOld);
//        if(stateOld.equals(state)){
            JSONObject json = weixinUtil.accessToken(code);
            JSONObject userinfo = weixinUtil.userinfo(json.getString("access_token"), json.getString("openid"));
//            JSONObject json1 = weixinUtil.auth(json.getString("access_token"), json.getString("openid"));
//            JSONObject json2 = weixinUtil.refreshToken(json.getString("refresh_token"));
            loginAuthReqBody.setOpenId(userinfo.getString("openid"));
            loginAuthReqBody.setNickname(userinfo.getString("nickname"));
            loginAuthReqBody.setHeadImgUrl(userinfo.getString("headimgurl"));
            loginAuthReqBody.setUnionid(userinfo.getString("unionid"));
            loginAuthReqBody.setPlatformType(2);//平台类型（1移动端、2PC端）
            loginAuthReqBody.setLoginType(2);//登录类型（1账号、2微信、3手机、4邮箱）
            return wechatService.loginWechatAuth(loginAuthReqBody);
//        }//否则，跳登录页
//        return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR);
    }

    @LogForController
    @ApiOperation(value = "【Android】微信登录授权")
    @RequestMapping(value = "/loginWechatAuth", method = RequestMethod.POST)
//    public Message<> loginWechatAuth(@RequestParam Map<String, Object> map) {//Content-Type=application/json;charset=UTF-8
    public Message<?> loginWechatAuth(@ApiParam(value = "必填参数：openId、nickname、headImgUrl、unionid,其他字段选填", required = true) @RequestBody LoginWechatAuthReqBody reqBody) {//Content-Type=application/x-www-form-urlencoded;charset=UTF-8
        log.info("【Android】微信登录授权");
        if (StringUtils.isBlank(reqBody.getOpenId())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getNickname())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getHeadImgUrl())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getUnionid())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        reqBody.setPlatformType(1);//平台类型（1移动端、2PC端）
        reqBody.setLoginType(2);//登录类型（1账号、2微信、3手机、4邮箱）
        return wechatService.loginWechatAuth(reqBody);
    }

    @LogForController
    @ApiOperation(value = "用户绑定、更换微信")
    @RequestMapping(value = "/bindWechat", method = RequestMethod.POST)
    public Message<?> bindWechat(@ApiParam(value = "必填参数：userId、nickname、headImgUrl、openId、unionid,其他字段选填", required = true) @RequestBody BindWechatReqBody reqBody) {
        log.info("用户绑定、更换微信");
        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getOpenId())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getNickname())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getHeadImgUrl())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getUnionid())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return wechatService.bindWechat(reqBody);
    }

    @LogForController
    @ApiOperation(value = "用户解除绑定微信")
    @RequestMapping(value = "/unbindWechat", method = RequestMethod.POST)
    public Message<?> unbindWechat(@ApiParam(value = "必填参数：userId、openId,其他字段选填", required = true) @RequestBody UnbindWechatReqBody reqBody) {
        log.info("用户解除绑定微信");
        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getOpenId())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return wechatService.unbindWechat(reqBody);
    }

}
