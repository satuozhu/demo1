package com.users.modules.user.controller;

import com.common.entity.user.CrAddress;
import com.common.entity.user.CrUserCertificationWithBLOBs;
import com.users.component.config.aspect.annotation.LogForController;
import com.users.component.entity.AreaCascader;
import com.users.component.entity.Message;
import com.users.component.entity.STATUS;
import com.users.modules.user.requestBody.crAddress.AddAddressReqBody;
import com.users.modules.user.requestBody.crAddress.DeleteAddressReqBody;
import com.users.modules.user.requestBody.crAddress.UpdateAddressReqBody;
import com.users.modules.user.requestBody.crAddress.UpdateDefaultAddressReqBody;
import com.users.modules.user.requestBody.crUserCertification.AddUserCertificationReqBody;
import com.users.modules.user.requestBody.crUserCertification.AuditUserCertificationReqBody;
import com.users.modules.user.requestBody.crUserCertification.UpdateUserCertificationReqBody;
import com.users.modules.user.service.CrAddressService;
import com.users.modules.user.service.CrUserCertificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 地址、身份验证控制器
 */
@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "*")
@Api(description = "地址、身份验证接口")
public class AddressController {

    @Autowired
    private CrAddressService crAddressService;
    @Autowired
    private CrUserCertificationService crUserCertificationService;

    @LogForController
    @ApiOperation(value = "添加收货地址")
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public Message<?> addAddress(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody AddAddressReqBody reqBody) {
        log.info("添加收货地址");
        if (null == reqBody.getAreaId() || reqBody.getAreaId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getStreet())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getDetailAddress())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getConcacts())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getPhone())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (reqBody.getDefaultAddress() != 0 && reqBody.getDefaultAddress() != 1) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return crAddressService.addAddress(reqBody);
    }

    @LogForController
    @ApiOperation(value = "查询当前用户的所有收货地址")
    @RequestMapping(value = "/selectAddressByUserId", method = RequestMethod.GET)
    public Message<List<CrAddress>> selectAddressByUserId(@ApiParam(value = "用户ID", required = true, defaultValue = "4") @RequestParam("userId") Long userId) {
        log.info("查询当前用户的所有收货地址");
        if (null == userId || userId == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return crAddressService.selectAddressByUserId(userId);
    }

    @LogForController
    @ApiOperation(value = "根据地址ID查询收货地址")
    @RequestMapping(value = "/selectAddressByAddressId", method = RequestMethod.GET)
    public Message<CrAddress> selectAddressByAddressId(@ApiParam(value = "地址ID", required = true) @RequestParam("addressId") Long addressId) {
        log.info("根据地址ID查询收货地址");
        if (null == addressId || addressId == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return crAddressService.selectAddressByAddressId(addressId);
    }

    @LogForController
    @ApiOperation(value = "根据地址ID删除收货地址")
    @RequestMapping(value = "/deleteAddressByAddressId", method = RequestMethod.POST)
    public Message<?> deleteAddressByAddressId(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody DeleteAddressReqBody reqBody) {
        log.info("根据地址ID删除收货地址");
        if (null == reqBody.getAddressId() || reqBody.getAddressId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return crAddressService.deleteAddressByAddressId(reqBody.getAddressId());
    }

    @LogForController
    @ApiOperation(value = "根据地址ID修改收货地址(只有一个默认地址)")
    @RequestMapping(value = "/updateAddressByAddressId", method = RequestMethod.POST)
    public Message<?> updateAddressByAddressId(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody UpdateAddressReqBody reqBody) {
        log.info("根据地址ID修改收货地址(只有一个默认地址)");
        if (null == reqBody.getId() || reqBody.getId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == reqBody.getAreaId() || reqBody.getAreaId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getStreet())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getDetailAddress())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getConcacts())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getPhone())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (reqBody.getDefaultAddress() != 0 && reqBody.getDefaultAddress() != 1) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return crAddressService.updateAddressByAddressId(reqBody);
    }

    @LogForController
    @ApiOperation(value = "修改为默认地址(只有一个默认地址)")
    @RequestMapping(value = "/updateDefaultAddress", method = RequestMethod.POST)
    public Message<?> updateDefaultAddress(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody UpdateDefaultAddressReqBody reqBody) {
        log.info("修改为默认地址(只有一个默认地址)");
        if (null == reqBody.getId() || reqBody.getId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (reqBody.getDefaultAddress() != 0 && reqBody.getDefaultAddress() != 1) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return crAddressService.updateDefaultAddress(reqBody);
    }

    @LogForController
    @ApiOperation(value = "获取所有省市区,组织为树状")
    @RequestMapping(value = "/selectAreas", method = {RequestMethod.GET})
    public Message<AreaCascader> selectAreas() {
        log.info("获取所有省市区,组织为树状");
        try {
            AreaCascader area = crAddressService.MenuArea();
            if (null != area) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, area);
            }
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    @LogForController
    @ApiOperation(value = "添加身份验证信息")
    @RequestMapping(value = "/addUserCertification", method = RequestMethod.POST)
    public Message<?> addUserCertification(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody AddUserCertificationReqBody reqBody) {
        log.info("添加身份验证信息");
        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getIdCardFront())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getIdCardBack())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getFaceAuth())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return crUserCertificationService.addUserCertification(reqBody);
    }

    @LogForController
    @ApiOperation(value = "查询当前用户的身份验证信息")
    @RequestMapping(value = "/selectUserCertificationByUserId", method = RequestMethod.GET)
    public Message<CrUserCertificationWithBLOBs> selectUserCertificationByUserId(@ApiParam(value = "用户ID", required = true, defaultValue = "4") @RequestParam("userId") Long userId) {
        log.info("查询当前用户的身份验证信息");
        if (null == userId || userId == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return crUserCertificationService.selectUserCertificationByUserId(userId);
    }

    @LogForController
    @ApiOperation(value = "根据身份ID修改身份验证信息")
    @RequestMapping(value = "/updateUserCertification", method = RequestMethod.POST)
    public Message<?> updateUserCertification(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody UpdateUserCertificationReqBody reqBody) {
        log.info("根据身份ID修改身份验证信息");
        if (null == reqBody.getId() || reqBody.getId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getIdCardFront())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getIdCardBack())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getFaceAuth())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return crUserCertificationService.updateUserCertification(reqBody);
    }

    @LogForController
    @ApiOperation(value = "根据身份ID执行身份验证图片识别")
    @RequestMapping(value = "/ocrUserCertification", method = RequestMethod.GET)
    public Message<?> ocrUserCertification(@ApiParam(value = "认证ID", required = true) @RequestParam("id") Long id) {
        log.info("根据身份ID执行身份验同证识别");
        return crUserCertificationService.ocrUserCertification(id);
    }

    @LogForController
    @ApiOperation(value = "根据身份ID执行身份验证信息审核")
    @RequestMapping(value = "/auditUserCertification", method = RequestMethod.POST)
    public Message<?> auditUserCertification(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody AuditUserCertificationReqBody reqBody) {
        log.info("根据身份ID执行身份验证信息审核");
        if (null == reqBody.getId() || reqBody.getId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == reqBody.getAuthStatus() || reqBody.getAuthStatus() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return crUserCertificationService.auditUserCertification(reqBody);
    }

}
