package com.users.modules.user.controller;

import com.users.component.config.aspect.annotation.LogForController;
import com.users.component.entity.Message;
import com.users.component.entity.MyPageInfo;
import com.users.component.entity.STATUS;
import com.users.component.util.IDCardUtil;
import com.users.modules.user.entity.CrUser;
import com.users.modules.user.service.CrUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Admin控制器
 */
@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "*")
@Api(description = "Admin接口")
public class AdminController {

    @Autowired
    private CrUserService crUserService;

    //http://192.168.1.21:8023/user/findCrUserWeb/1/5
    @LogForController
    @ApiOperation(value = "【admin在使用】查询全部用户个人信息")
    @RequestMapping(value = "/findCrUserWeb/{pageNum}/{pageSize}", method = RequestMethod.POST)
    public Message<MyPageInfo<CrUser>> findCrUserWeb(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody CrUser crUser,
                                                     @ApiParam(value = "当前页（默认1）", required = true, defaultValue = "1") @PathVariable Integer pageNum,
                                                     @ApiParam(value = "一页几行", required = true, defaultValue = "5") @PathVariable Integer pageSize) {
        log.info("【admin在使用】询全部用户个人信息");
        return crUserService.findCrUserWeb(crUser, pageNum, pageSize);
    }

    @LogForController
    @ApiOperation(value = "【admin在使用】根据用户ID更新用户个人信息")
    @RequestMapping(value = "/updateCrUser", method = RequestMethod.PUT)
//    @RequestMapping(value = "/updateCrUserWeb", method = RequestMethod.POST)
    public Message<?> updateCrUserWeb(@ApiParam(value = "必填参数：id,其他字段选填", required = true) @RequestBody CrUser crUser) {
        log.info("【admin在使用】根据用户ID更新用户个人信息");
        if (null == crUser.getId() || crUser.getId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return crUserService.updateCrUserWeb(crUser);
    }

    @LogForController
    @ApiOperation(value = "【admin在使用】根据用户ID删除用户个人信息")
    @RequestMapping(value = "/delCrUserWebByUserId", method = RequestMethod.DELETE)
    //public Message1 delCrUserByUserId(@RequestParam("userId") Long userId) {
    public Message<?> delCrUserWebByUserId(@ApiParam(value = "必填参数：id,其他字段不填", required = true) @RequestBody CrUser crUser) {
        Long userId = crUser.getId();
        log.info("【admin在使用】根据用户ID删除用户个人信息");
        if (null == userId || userId == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return crUserService.delCrUserWebByUserId(userId);
    }

}
