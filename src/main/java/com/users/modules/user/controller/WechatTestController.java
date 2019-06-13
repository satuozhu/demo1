package com.users.modules.user.controller;

import com.users.component.config.aspect.annotation.LogForController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
//@RequestMapping
public class WechatTestController {

    /**
     * http://localhost:80/user/wechat/logins2
     * http://192.168.1.21:80/user/wechat/logins2
     * http://www.szcrain.com/user/wechat/logins2
     * http://www.szcrain.com:80/user/wechat/logins2
     *
     * http://localhost:80/views/qrCode.html
     * https://www.szcrain.com/user/swagger-ui.html
     * http://www.szcrain.com:8000/user/swagger-ui.html
     * https://www.szcrain.com:8000/user/swagger-ui.html
     * 【微信网页授权】重定向访问二维码页面
     * @return
     */
    @ApiIgnore // 屏蔽（内部使用，不对外）
    @LogForController
    @RequestMapping(value = "/user/wechat/logins2", method = RequestMethod.GET)
    public String logins(HttpServletResponse response) throws IOException {
//        String redirectURL = "/views/qrCode.html";
//        String redirectURL = "http://localhost:8080/views/qrCode.html";

//        response.sendRedirect(redirectURL);
        return "views/qrCode";
    }

}
