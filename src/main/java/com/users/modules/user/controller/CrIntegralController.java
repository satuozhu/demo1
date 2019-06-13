package com.users.modules.user.controller;

import com.common.entity.user.CrIntegral;
import com.common.entity.user.CrQuestion;
import com.common.entity.user.CrSysMessage;
import com.users.component.config.aspect.annotation.LogForController;
import com.users.component.entity.Message;
import com.users.component.entity.MyPageInfo;
import com.users.component.entity.STATUS;
import com.users.modules.user.requestBody.crAppointment.*;
import com.users.modules.user.responseBody.crAppointment.CrIntegralDetailResBody;
import com.users.modules.user.service.AppointmentService;
import com.users.modules.user.service.ICrIntegralService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 积分、消息控制器
 */
@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "*")
@Api(description = "积分、消息、咨询接口")
public class CrIntegralController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private ICrIntegralService iCrIntegralService;

    @LogForController
    @ApiOperation(value = "【咨询】查询最新的3条提问")
    @RequestMapping(value = "/selectCrQuestionTop", method = RequestMethod.POST)
    public Message<List<CrQuestion>> selectCrQuestionTop(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody CrQuestionTopReqBody reqBody) {
        log.info("【咨询】查询最新的3条提问");
        return appointmentService.selectCrQuestionTop(reqBody);
    }

    @LogForController
    @ApiOperation(value = "【咨询】添加提问")
    @RequestMapping(value = "/addCrQuestion", method = RequestMethod.POST)
    public Message<?> addCrQuestion(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody AddCrQuestionReqBody reqBody) {
        log.info("【咨询】添加提问");
        if (null == reqBody.getType()) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getContent())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == reqBody.getBeanId()) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return appointmentService.addCrQuestion(reqBody);
    }

    @LogForController
    @ApiOperation(value = "【咨询】回复提问")
    @RequestMapping(value = "/replyCrQuestion", method = RequestMethod.POST)
    public Message<?> replyCrQuestion(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody ReplyCrQuestionReqBody reqBody) {
        log.info("【咨询】回复提问");
        if (null == reqBody.getId() || reqBody.getId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getReplyContent())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return appointmentService.replyCrQuestion(reqBody);
    }

    @LogForController
    @ApiOperation(value = "【消息】查询推送的消息")
    @RequestMapping(value = "/selectCrSysMessageList", method = RequestMethod.POST)
    public Message<MyPageInfo<CrSysMessage>> selectCrSysMessageList(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody CrSysMessageListReqBody reqBody) {
        log.info("【消息】查询推送的消息");
        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return appointmentService.selectCrSysMessageList(reqBody);
    }

    @LogForController
    @ApiOperation(value = "【消息】更新消息状态为已读")
    @RequestMapping(value = "/updateCrSysMessage", method = RequestMethod.POST)
    public Message<?> updateCrSysMessage(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody UpdateCrSysMessageReqBody reqBody) {
        log.info("【消息】更新消息状态为已读");
        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == reqBody.getMessageId() || reqBody.getMessageId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return appointmentService.updateCrSysMessage(reqBody);
    }

    @LogForController
    @ApiOperation(value = "【积分】添加积分记录(test测试用)")
    @RequestMapping(value = "/addCrIntegralDetail", method = RequestMethod.POST)
    public Message<?> addCrIntegralDetail(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody AddCrIntegralDetailReqBody reqBody) {
        log.info("【积分】添加积分记录(test测试用)");
        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getType())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return iCrIntegralService.addCrIntegralDetail(reqBody);
    }

    @LogForController
    @ApiOperation(value = "【积分】【我的积分】分页查询最近30天的积分明细")
    @RequestMapping(value = "/selectCrIntegralDetailList", method = RequestMethod.POST)
    public Message<MyPageInfo<CrIntegralDetailResBody>> selectCrIntegralDetailList(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody CrIntegralDetailListReqBody reqBody) {
        log.info("【积分】【我的积分】分页查询最近30天的积分明细");
        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == reqBody.getPageNum() || reqBody.getPageNum() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == reqBody.getPageSize() || reqBody.getPageSize() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return iCrIntegralService.selectCrIntegralDetailList(reqBody);
    }

    @LogForController
    @ApiOperation(value = "根据用户ID查询用户的积分")
    @RequestMapping(value = "/selectCrIntegralByUserId", method = RequestMethod.GET)
    public Message<CrIntegral> selectCrIntegralByUserId(@ApiParam(value = "用户ID", required = true, defaultValue = "4") @RequestParam("userId") Long userId) {
        log.info("根据用户ID查询用户的积分");
        if (null == userId || userId == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return iCrIntegralService.selectCrIntegralByUserId(userId);
    }

}
