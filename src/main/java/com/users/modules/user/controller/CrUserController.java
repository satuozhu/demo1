package com.users.modules.user.controller;

import com.common.entity.user.CrDoctorComment;
import com.users.component.config.aspect.annotation.LogForController;
import com.users.component.entity.Message;
import com.users.component.entity.MyPageInfo;
import com.users.component.entity.STATUS;
import com.users.component.util.IDCardUtil;
import com.users.modules.user.entity.CrUser;
import com.users.modules.user.responseBody.CrUserResBody;
import com.users.modules.user.service.CrUserService;
import com.users.modules.user.service.ICrDoctorCommentService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "*")
@Api(description = "用户接口")
public class CrUserController {

    @Autowired
    private CrUserService crUserService;
    @Autowired
    private ICrDoctorCommentService doctorCommentService;
    @LogForController
    @ApiOperation(value = "根据用户ID查询用户个人信息")
    @RequestMapping(value = "/findCrUserByUserId", method = RequestMethod.GET)
    public Message<CrUserResBody> findCrUserByUserId(@ApiParam(value = "用户ID", required = true) @RequestParam("userId") Long userId) {
        log.info("根据用户ID查询用户个人信息");
        if (null == userId || userId == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return crUserService.findCrUserByUserId(userId);
    }

    @LogForController
    @ApiOperation(value = "根据用户ID更新用户个人信息")
//    @RequestMapping(value = "/updateCrUser", method = RequestMethod.PUT)
    @RequestMapping(value = "/updateCrUser", method = RequestMethod.POST)
    public Message<?> updateCrUser(@ApiParam(value = "必填参数：id,其他字段选填", required = true) @RequestBody CrUser crUser) {
        log.info("根据用户ID更新用户个人信息");
        if (null == crUser.getId() || crUser.getId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return crUserService.updateCrUser(crUser);
    }

    @ApiIgnore // 屏蔽（内部使用，不对外）
    @LogForController
    @ApiOperation(value = "根据账号查询用户个人信息")
    @RequestMapping(value = "/findCrUserByAccount", method = RequestMethod.GET)
    public Message<CrUser> findCrUserByAccount(@ApiParam(value = "用户账号", required = true) @RequestParam("account") String account) {
        log.info("根据账号查询用户个人信息");
        if (StringUtils.isBlank(account)) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        try {
            CrUser crUser = crUserService.findCrUserByAccount(account);
            if (crUser != null) {
                crUser.setPwd("");
                crUser.setSalt("");
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, crUser);
            }
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    @ApiIgnore // 屏蔽（内部使用，不对外）
    @LogForController
    @ApiOperation(value = "根据手机号查询用户个人信息")
    @RequestMapping(value = "/findCrUserByPhone", method = RequestMethod.GET)
    public Message<CrUser> findCrUserByPhone(@ApiParam(value = "手机号", required = true) @RequestParam("phone") String phone) {
        log.info("根据手机号查询用户个人信息");
        if (StringUtils.isBlank(phone)) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        try {
            CrUser crUser = crUserService.findCrUserByPhone(phone);
            if (crUser != null) {
                crUser.setPwd("");
                crUser.setSalt("");
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, crUser);
            }
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    @ApiIgnore // 屏蔽（内部使用，不对外）
    @LogForController
    @ApiOperation(value = "根据邮箱查询用户个人信息")
    @RequestMapping(value = "/findCrUserByEmail", method = RequestMethod.GET)
    public Message<CrUser> findCrUserByEmail(@ApiParam(value = "邮箱", required = true) @RequestParam("email") String email) {
        log.info("根据邮箱查询用户个人信息");
        if (StringUtils.isBlank(email)) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        try {
            CrUser crUser = crUserService.findCrUserByEmail(email);
            if (crUser != null) {
                crUser.setPwd("");
                crUser.setSalt("");
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, crUser);
            }
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    @ApiIgnore // 屏蔽（内部使用，不对外）
    @LogForController
    @ApiOperation(value = "根据微信openid查询用户个人信息")
    @RequestMapping(value = "/findCrUserByOpenid", method = RequestMethod.GET)
    public Message<CrUser> findCrUserByOpenid(@ApiParam(value = "微信openid", required = true) @RequestParam("openid") String openid) {
        log.info("根据微信openid查询用户个人信息");
        if (StringUtils.isBlank(openid)) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        try {
            CrUser crUser = crUserService.findCrUserByOpenid(openid);
            if (crUser != null) {
                crUser.setPwd("");
                crUser.setSalt("");
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, crUser);
            }
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    //http://192.168.1.21:8023/user/findCrUser?pageNum=1&pageSize=5
//    @ApiIgnore // 屏蔽（内部使用，不对外）
    @LogForController
    @ApiOperation(value = "查询全部用户个人信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页（默认1）", required = true, dataType = "int", paramType = "query", defaultValue = "1")
            , @ApiImplicitParam(name = "pageSize", value = "一页几行", required = true, dataType = "int", paramType = "query", defaultValue = "5")})
    @RequestMapping(value = "/findCrUser", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})//, produces = "application/json"
    public Message<MyPageInfo<CrUser>> findCrUser(HttpServletRequest request, @RequestParam Integer pageNum, @RequestParam Integer pageSize,
                                                  @ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody CrUser crUser) {
        log.info("询全部用户个人信息");
        return crUserService.findCrUser(crUser, pageNum, pageSize);
    }

    @ApiIgnore // 屏蔽（内部使用，不对外）
    @LogForController
    @ApiOperation(value = "根据用户ID删除用户个人信息")
    @RequestMapping(value = "/delCrUserByUserId", method = RequestMethod.DELETE)
    public Message<?> delCrUserByUserId(@ApiParam(value = "必填参数：id,其他字段不填", required = true) @RequestBody CrUser crUser) {
        Long userId = crUser.getId();
        log.info("根据用户ID删除用户个人信息");
        if (null == userId || userId == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return crUserService.delCrUserByUserId(userId);
    }

    @ApiIgnore // 屏蔽（内部使用，不对外）
    @LogForController
    @ApiOperation(value = "身份证验证")
    @RequestMapping(value = "/idCardNoAuth", method = RequestMethod.GET)
    public Message<?> idCardNoAuth(@ApiParam(value = "身份证号码", required = true) @RequestParam("idCardNo") String idCardNo) {
        log.info("身份证验证");
        if (StringUtils.isBlank(idCardNo)) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        try {
            //校验身份证
            Boolean isTrue = IDCardUtil.isIDCard(idCardNo);
            if (isTrue) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_YES_ID_CARD, isTrue);
            }
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_NO_ID_CARD, isTrue);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }
    @LogForController
    @ApiOperation(value="H5/根据用户编号查询评论信息")
        @RequestMapping(value = "/messageForDoctor/{id}",method = RequestMethod.GET)
    public ModelAndView messageForDoctor(@ApiParam(value = "用户编号") @PathVariable("id") Long id,Model model){
        List<CrDoctorComment> list = doctorCommentService.queryCommentsByUserId(id);
        model.addAttribute("list",list);
        return new ModelAndView("views/userMessage","messageModel",model);
    }
    @LogForController
    @ApiOperation(value="根据用户编号查询评论信息")
    @RequestMapping(value = "/messageForDoctor2/{id}",method = RequestMethod.GET)
    public Message messageForDoctor2(@ApiParam(value = "用户编号") @PathVariable("id") Long id){
        Message message = new Message();
        List<CrDoctorComment> list = doctorCommentService.queryCommentsByUserId(id);
        message.setCode(STATUS.CODE_SUCCESS);
        message.setData(list);
        return message;
    }

}
