package com.users.modules.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.common.entity.user.CrAppointmentTime;
import com.common.entity.user.CrCase;
import com.users.component.config.aspect.annotation.LogForController;
import com.users.component.entity.*;
import com.users.modules.user.requestBody.crAppointment.*;
import com.users.modules.user.responseBody.crAppointment.*;
import com.users.modules.user.service.AppointmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 预约相关控制器
 */
@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "*")
@Api(description = "预约相关接口")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @LogForController
    @ApiOperation(value = "根据医生ID查询预约信息")
    @RequestMapping(value = "/selectAppointmentByDoctorId", method = RequestMethod.GET)
    public Message<List<AppointmentByDoctorIdResBody>> selectAppointmentByDoctorId(@ApiParam(value = "医生ID", required = true, defaultValue = "1") @RequestParam("doctorId") Long doctorId) {
        log.info("根据医生ID查询预约信息");
        return appointmentService.selectAppointmentByDoctorId(doctorId);
    }

    @LogForController
    @ApiOperation(value = "根据医生ID查询预约信息列表（包含患者信息）")
    @RequestMapping(value = "/selectAppointmentByDoctorId2", method = RequestMethod.POST)
    public Message<MyPageInfo<AppointmentByDoctorIdResBody>> selectAppointmentByDoctorId2(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody AppointmentByDoctorIdReqBody reqBody) {
        log.info("根据医生ID查询预约信息");
        if (null == reqBody.getDoctorId() || reqBody.getDoctorId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return appointmentService.selectAppointmentByDoctorId2(reqBody);
    }

    @LogForController
    @ApiOperation(value = "【预约】【我的预约详情】根据预约ID查看预约信息")
    @RequestMapping(value = "/selectAppointmentById", method = RequestMethod.GET)
    public Message<AppointmentByIdResBody> selectAppointmentById(@ApiParam(value = "预约编号", required = true, defaultValue = "577458123741069312") @RequestParam("appointmentId") Long appointmentId) {
        log.info("【预约】【我的预约详情】根据预约ID查看预约信息");
        if (null == appointmentId || appointmentId == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return appointmentService.selectAppointmentById(appointmentId);
    }

    @LogForController
    @ApiOperation(value = "【医生】【汇名医】医生好评率top3")
    @RequestMapping(value = "/selectDoctorInfoTop", method = RequestMethod.POST)
    public Message<List<SelectDoctorInfoTopResBody>> selectDoctorInfoTop() {
        log.info("【医生】【汇名医】医生好评率top3");
        return appointmentService.selectDoctorInfoTop();
    }

//    @LogForController
//    @ApiOperation(value = "【预约】【我的预约】用户查看预约")
//    @RequestMapping(value = "/selectAppointmentOne", method = RequestMethod.POST)
//    public Message<AppointmentListResBody> selectAppointmentOne(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody AppointmentListReqBody reqBody) {
//        log.info("【预约】【我的预约】用户查看预约");
//        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
//        return appointmentService.selectAppointmentOne(reqBody);
//    }
    @LogForController
    @ApiOperation(value = "【预约】【我的预约】用户查看预约列表")
    @RequestMapping(value = "/selectAppointmentList", method = RequestMethod.POST)
    public Message<MyPage<AppointmentResBody>> selectAppointmentList(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody AppointmentListReqBody reqBody) {
        log.info("【预约】【我的预约】用户查看预约列表");
        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        try {
            List<AppointmentListResBody> listResBody = appointmentService.selectAppointmentList(reqBody);
            List<AppointmentResBody> list = new ArrayList<>();
            AppointmentResBody resBody;
            for (AppointmentListResBody al : listResBody) {
                resBody = new AppointmentResBody();
                resBody.setId(al.getId());
                resBody.setStatus(al.getStatus());
                resBody.setAppointment(al);
                list.add(resBody);
            }
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, new MyPage<>(list, reqBody.getPageNum(), reqBody.getPageSize()));
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }
//    @LogForController
//    @ApiOperation(value = "【预约】【我的预约】用户查看预约列表")
//    @RequestMapping(value = "/selectAppointmentList", method = RequestMethod.POST)
//    public Message<MyPageInfo<AppointmentListResBody>> selectAppointmentList(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody AppointmentListReqBody reqBody) {
//        log.info("【预约】【我的预约】用户查看预约列表");
//        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
//            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
//        }
//        return appointmentService.selectAppointmentList(reqBody);
//    }

    @LogForController
    @ApiOperation(value = "【预约】【问诊记录】用户查看历史预约列表")
    @RequestMapping(value = "/selectAppointmentHistoryList", method = RequestMethod.POST)
    public Message<MyPageInfo<AppointmentHistoryListResBody>> selectAppointmentHistoryList(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody AppointmentHistoryListReqBody reqBody) {
        log.info("【预约】【问诊记录】用户查看历史预约列表");
        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return appointmentService.selectAppointmentHistoryList(reqBody);
    }

    @LogForController
    @ApiOperation(value = "【医生】【医生介绍页面-弹出框形式】查询医生经典案例")
    @RequestMapping(value = "/selectDoctorCase", method = RequestMethod.POST)
    public Message<MyPageInfo<CrCase>> selectDoctorCaseList(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody DoctorCaseReqBody reqBody) {
        log.info("【医生】【医生介绍页面-弹出框形式】查询医生经典案例");
        if (null == reqBody.getDoctorId() || reqBody.getDoctorId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return appointmentService.selectDoctorCaseList(reqBody);
    }

    @LogForController
    @ApiOperation(value = "【医生】【医生介绍页面-弹出框形式】根据医生编号查询医生个人介绍")
    @RequestMapping(value = "/selectDoctorInfoByDoctorId", method = RequestMethod.GET)
    public Message<DoctorInfoByIdResBody> selectDoctorInfoByDoctorId(@ApiParam(value = "医生编号", required = true, defaultValue = "1") @RequestParam("doctorId") Long doctorId) {
        log.info("【医生】【医生介绍页面-弹出框形式】根据医生编号查询医生个人介绍");
        if (null == doctorId || doctorId == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return appointmentService.selectDoctorInfoByDoctorId(doctorId);
    }

    @LogForController
    @ApiOperation(value = "【医生】【我的医生】根据患者编号查询我的医生")
    @RequestMapping(value = "/selectDoctorInfoList", method = RequestMethod.POST)
    public Message<MyPageInfo<DoctorInfoByUserIdResBody>> selectDoctorInfoList(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody DoctorInfoListReqBody reqBody) {
        log.info("【医生】【我的医生】根据患者编号查询我的医生");
        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return appointmentService.selectDoctorInfoList(reqBody);
    }

    @LogForController
    @ApiOperation(value = "【处方】【我的处方详情】根据处方ID查询处方")
    @RequestMapping(value = "/selectPrescriptionById", method = RequestMethod.GET)
    public Message<PrescriptionByIdResBody> selectPrescriptionById(@ApiParam(value = "患者ID", required = true, defaultValue = "1") @RequestParam("prescriptionId") Long prescriptionId) {
        log.info("【处方】【我的处方详情】根据处方ID查询处方");
        if (null == prescriptionId || prescriptionId == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return appointmentService.selectPrescriptionById(prescriptionId);
    }

    @LogForController
    @ApiOperation(value = "【处方】【我的处方】根据患者编号查询我的处方")
    @RequestMapping(value = "/selectPrescriptionList", method = RequestMethod.POST)
    public Message<MyPageInfo<PrescriptionByUserIdResBody>> selectPrescriptionList(@ApiParam(value = "必填参数：,其他字段选填", required = true) @RequestBody PrescriptionListReqBody reqBody) {
        log.info("【处方】【我的处方】根据患者编号查询我的处方");
        if (null == reqBody.getUserId() || reqBody.getUserId() == 0) {
            return new Message<>(STATUS.CODE_NULL, STATUS.MSG_NULL);
        }
        return appointmentService.selectPrescriptionList(reqBody);
    }

}
