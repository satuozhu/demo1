package com.users.modules.user.controller;

import com.common.entity.user.CrAppointmentTime;
import com.users.component.config.aspect.annotation.LogForController;
import com.users.component.entity.Message;
import com.users.component.entity.MyPageInfo;
import com.users.component.entity.STATUS;
import com.users.modules.user.requestBody.crAppointment.ConfirmAppointmentAgainReqBody;
import com.users.modules.user.requestBody.crAppointment.NormalHouseListReqBody;
import com.users.modules.user.requestBody.crAppointment.NormalHouseReqBody;
import com.users.modules.user.requestBody.crAppointment.SearchDoctorInfoReqBody;
import com.users.modules.user.responseBody.crAppointment.DoctorHouseTimeResBody;
import com.users.modules.user.responseBody.crAppointment.SearchDoctorInfoResBody;
import com.users.modules.user.responseBody.crAppointment.SelectNormalHouseListResBody;
import com.users.modules.user.responseBody.crAppointment.SelectNormalHouseResBody;
import com.users.modules.user.service.AppointmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 预约流程控制器
 */
@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "*")
@Api(description = "预约流程接口")
public class AppointmentProcedureController {

    @Autowired
    private AppointmentService appointmentService;

    @LogForController
    @ApiOperation(value = "【医生】【1选择医生】搜索医生列表")
    @RequestMapping(value = "/searchDoctorInfoList", method = RequestMethod.POST)
    public Message<MyPageInfo<SearchDoctorInfoResBody>> searchDoctorInfoList(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody SearchDoctorInfoReqBody reqBody) {
        log.info("【医生】【选择医生】搜索医生列表");
//        if (StringUtils.isBlank(reqBody.getDoctorName())) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
        if (null == reqBody.getDoctorName()) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return appointmentService.searchDoctorInfoList(reqBody);
    }

    @LogForController
    @ApiOperation(value = "【诊室】【2看地图选诊室】查询正常运转的诊室（医生-诊室-时间段）")
    @RequestMapping(value = "/selectNormalHouseList", method = RequestMethod.POST)
    public Message<MyPageInfo<SelectNormalHouseListResBody>> selectNormalHouseList(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody NormalHouseListReqBody reqBody) {
        log.info("【诊室】【看地图选诊室】查询正常运转的诊室（医生-诊室-时间段）");
        if (null == reqBody.getAreaId() || reqBody.getAreaId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
//        if (StringUtils.isBlank(reqBody.getHouseName())) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
        return appointmentService.selectNormalHouseList(reqBody);
    }

    @LogForController
    @ApiOperation(value = "【诊室】【2看地图选诊室】查询正常运转的诊室（医生-时间段-诊室）")
    @RequestMapping(value = "/selectNormalHouse", method = RequestMethod.POST)
    public Message<MyPageInfo<SelectNormalHouseResBody>> selectNormalHouse(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody NormalHouseReqBody reqBody) {
        log.info("【诊室】【看地图选诊室】查询正常运转的诊室（医生-时间段-诊室）");
        if (null == reqBody.getAreaId() || reqBody.getAreaId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
//        if (StringUtils.isBlank(reqBody.getHouseName())) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
        if (StringUtils.isBlank(reqBody.getDate())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getTimeSlot())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return appointmentService.selectNormalHouse(reqBody);
    }

    @LogForController
    @ApiOperation(value = "【就诊时间段】【3选择就诊时间】查询医生某一天的所有预约时间（医生-时间段-诊室）")
    @RequestMapping(value = "/selectDoctorAppointmentTime", method = RequestMethod.GET)
    public Message<List<CrAppointmentTime>> selectDoctorAppointmentTime(@ApiParam(value = "医生编号", required = true, defaultValue = "1") @RequestParam("doctorId") Long doctorId
            , @ApiParam(value = "日期2019-04-18", required = true, defaultValue = "2019-06-03") @RequestParam String date) {
        log.info("【就诊时间段】【选择就诊时间】查询医生某一天的所有预约时间（医生-时间段-诊室）");
        if (null == doctorId || doctorId == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(date)) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return appointmentService.selectDoctorAppointmentTime(doctorId, date);
    }

    @LogForController
    @ApiOperation(value = "【就诊时间段】【3选择就诊时间】查询医生和诊室某一天的所有预约时间（医生-诊室-时间段）")
    @RequestMapping(value = "/selectDoctorHouseTime", method = RequestMethod.GET)
    public Message<List<DoctorHouseTimeResBody>> selectDoctorHouseTime(@ApiParam(value = "医生编号", required = true, defaultValue = "1") @RequestParam("doctorId") Long doctorId
            , @ApiParam(value = "诊室编号", required = true, defaultValue = "1") @RequestParam("houseId") Long houseId
            , @ApiParam(value = "日期2019-04-18", required = true, defaultValue = "2019-06-03") @RequestParam String date) {
        log.info("【就诊时间段】【3选择就诊时间】查询医生和诊室某一天的所有预约时间（医生-诊室-时间段）");
        if (null == doctorId || doctorId == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == houseId || houseId == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(date)) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return appointmentService.selectDoctorHouseTime(doctorId, houseId, date);
    }

    @LogForController
    @ApiOperation(value = "【预约】【预约成功】确认预约")
    @RequestMapping(value = "/confirmAppointment", method = RequestMethod.POST)
    public Message<?> confirmAppointment(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody ConfirmAppointmentAgainReqBody reqBody) {
        log.info("【预约】【预约成功】确认预约");
        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
//        if (StringUtils.isBlank(reqBody.getUserName())) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
        if (null == reqBody.getDoctorId() || reqBody.getDoctorId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
//        if (StringUtils.isBlank(reqBody.getDoctorName())) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
        if (null == reqBody.getHouseId() || reqBody.getHouseId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getHouseName())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == reqBody.getAppointmentTimeId() || reqBody.getAppointmentTimeId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
//        if (null == reqBody.getConsultPrice() || reqBody.getConsultPrice().compareTo(BigDecimal.ZERO) == 0) {
        if (null == reqBody.getConsultPrice() || reqBody.getConsultPrice().equals(BigDecimal.ZERO)) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getAppointmentDate())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (StringUtils.isBlank(reqBody.getTimeSlot())) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        if (null == reqBody.getAppointmentId() || reqBody.getAppointmentId() == 0) {
            return appointmentService.confirmAppointment(reqBody);
        } else {
            return appointmentService.confirmAppointmentAgain(reqBody);
        }
    }

//    @LogForController
//    @ApiOperation(value = "【预约】【4初诊预约成功】确认初诊预约")
//    @RequestMapping(value = "/confirmAppointment", method = RequestMethod.POST)
//    public Message<?> confirmAppointment(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody ConfirmAppointmentReqBody reqBody) {
//        log.info("【预约】【预约成功】确认预约");
//        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
//        if (null == reqBody.getDoctorId() || reqBody.getDoctorId() == 0) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
//        if (null == reqBody.getHouseId() || reqBody.getHouseId() == 0) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
//        if (null == reqBody.getAppointmentTimeId() || reqBody.getAppointmentTimeId() == 0) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
////        if (null == reqBody.getConsultPrice() || reqBody.getConsultPrice().compareTo(BigDecimal.ZERO) == 0) {
//        if (null == reqBody.getConsultPrice() || reqBody.getConsultPrice().equals(BigDecimal.ZERO)) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
//        if (StringUtils.isBlank(reqBody.getAppointmentDate())) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
//        if (StringUtils.isBlank(reqBody.getTimeSlot())) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
//        return appointmentService.confirmAppointment(reqBody);
//    }
//
//    @LogForController
//    @ApiOperation(value = "【预约】确认复诊预约(前提条件,预约已完成,已设置复诊)")
//    @RequestMapping(value = "/confirmAppointmentAgain", method = RequestMethod.POST)
//    public Message<?> confirmAppointmentAgain(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody ConfirmAppointmentAgainReqBody reqBody) {
//        log.info("【复诊】确认复诊");
//        //(当前预约单,复诊前提条件status=5已完成,again_status=1,appointment_id=0)
//        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
//        if (null == reqBody.getDoctorId() || reqBody.getDoctorId() == 0) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
//        if (null == reqBody.getHouseId() || reqBody.getHouseId() == 0) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
//        if (null == reqBody.getAppointmentTimeId() || reqBody.getAppointmentTimeId() == 0) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
////        if (null == reqBody.getConsultPrice() || reqBody.getConsultPrice().compareTo(BigDecimal.ZERO) == 0) {
//        if (null == reqBody.getConsultPrice() || reqBody.getConsultPrice().equals(BigDecimal.ZERO)) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
//        if (StringUtils.isBlank(reqBody.getAppointmentDate())) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
//        if (StringUtils.isBlank(reqBody.getTimeSlot())) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
//        return appointmentService.confirmAppointmentAgain(reqBody);
//    }

    //【我要复诊】查看我的医生，设置复诊的出现复诊按钮，点击复诊，选择诊室、时间，添加复诊记录
    //预约开锁问题

}