package com.users.modules.user.service;

import com.common.entity.user.CrAppointmentTime;
import com.common.entity.user.CrCase;
import com.common.entity.user.CrQuestion;
import com.common.entity.user.CrSysMessage;
import com.users.component.entity.Message;
import com.users.component.entity.MyPage;
import com.users.component.entity.MyPageInfo;
import com.users.component.entity.Page;
import com.users.modules.user.requestBody.crAppointment.*;
import com.users.modules.user.responseBody.crAppointment.*;

import java.util.List;

/**
 * 预约service接口
 */
public interface AppointmentService {

    /**
     * 查询最新的3条提问
     *
     * @return
     */
    Message<List<CrQuestion>> selectCrQuestionTop(CrQuestionTopReqBody reqBody);

    /**
     * 添加提问
     *
     * @param reqBody
     * @return
     */
    Message<?> addCrQuestion(AddCrQuestionReqBody reqBody);

    /**
     * 回复提问
     *
     * @param reqBody
     * @return
     */
    Message<?> replyCrQuestion(ReplyCrQuestionReqBody reqBody);

    /**
     * 查询推送的消息
     *
     * @return
     */
    Message<MyPageInfo<CrSysMessage>> selectCrSysMessageList(CrSysMessageListReqBody reqBody);

    /**
     * 更新消息状态为已读
     *
     * @param reqBody
     * @return
     */
    Message<?> updateCrSysMessage(UpdateCrSysMessageReqBody reqBody);

    /**
     * 根据医生ID查询预约信息
     *
     * @param doctorId
     * @return
     */
    Message<List<AppointmentByDoctorIdResBody>> selectAppointmentByDoctorId(Long doctorId);

    /**
     * 根据医生ID查询预约信息列表
     *
     * @param reqBody
     * @return
     */
    Message<MyPageInfo<AppointmentByDoctorIdResBody>> selectAppointmentByDoctorId2(AppointmentByDoctorIdReqBody reqBody);

    /**
     * 医生好评率top3
     *
     * @return
     */
    Message<List<SelectDoctorInfoTopResBody>> selectDoctorInfoTop();

    /**
     * 根据医生编号查询详情
     *
     * @return
     */
    SelectDoctorInfoTopResBody selectDoctorInfoById(Long userId);

    /**
     * 搜索医生列表
     *
     * @param reqBody
     * @return
     */
    Message<MyPageInfo<SearchDoctorInfoResBody>> searchDoctorInfoList(SearchDoctorInfoReqBody reqBody);

    /**
     * 查询正常运转的诊室（医生-诊室-时间段）
     *
     * @param reqBody
     * @return
     */
    Message<MyPageInfo<SelectNormalHouseListResBody>> selectNormalHouseList(NormalHouseListReqBody reqBody);

    /**
     * 查询正常运转的诊室（医生-时间段-诊室）
     *
     * @param reqBody
     * @return
     */
    Message<MyPageInfo<SelectNormalHouseResBody>> selectNormalHouse(NormalHouseReqBody reqBody);

    /**
     * 查询医生某一天的所有预约时间（医生-时间段-诊室）
     *
     * @param doctorId
     * @param date
     * @return
     */
    Message<List<CrAppointmentTime>> selectDoctorAppointmentTime(Long doctorId, String date);

    /**
     * 查询医生和诊室某一天的所有预约时间（医生-诊室-时间段）
     *
     * @param doctorId
     * @param houseId
     * @param date
     * @return
     */
    Message<List<DoctorHouseTimeResBody>> selectDoctorHouseTime(Long doctorId, Long houseId, String date);

    /**
     * 确认初诊预约
     *
     * @param reqBody
     * @return
     */
    Message<?> confirmAppointment(ConfirmAppointmentAgainReqBody reqBody);

    /**
     * 确认复诊复诊
     *
     * @param reqBody
     * @return
     */
    Message<?> confirmAppointmentAgain(ConfirmAppointmentAgainReqBody reqBody);

    /**
     * 根据预约ID查看预约信息
     *
     * @param appointmentId
     * @return
     */
    Message<AppointmentByIdResBody> selectAppointmentById(Long appointmentId);

    /**
     * 用户查看预约列表
     *
     * @param reqBody
     * @return
     */
//    Message<AppointmentListResBody> selectAppointmentOne(AppointmentListReqBody reqBody);
    List<AppointmentListResBody> selectAppointmentList(AppointmentListReqBody reqBody);
//    Message<MyPageInfo<AppointmentListResBody>> selectAppointmentList(AppointmentListReqBody reqBody);

    /**
     * 用户查看历史预约列表
     *
     * @param reqBody
     * @return
     */
    Message<MyPageInfo<AppointmentHistoryListResBody>> selectAppointmentHistoryList(AppointmentHistoryListReqBody reqBody);

    /**
     * 查询医生经典案例
     *
     * @param reqBody
     * @return
     */
    Message<MyPageInfo<CrCase>> selectDoctorCaseList(DoctorCaseReqBody reqBody);

    /**
     * 根据医生编号查询医生信息
     *
     * @param doctorId
     * @return
     */
    Message<DoctorInfoByIdResBody> selectDoctorInfoByDoctorId(Long doctorId);

    /**
     * 根据患者编号查询我的医生
     *
     * @param reqBody
     * @return
     */
    Message<MyPageInfo<DoctorInfoByUserIdResBody>> selectDoctorInfoList(DoctorInfoListReqBody reqBody);

    /**
     * 根据处方ID查询处方
     *
     * @param prescriptionId
     * @return
     */
    Message<PrescriptionByIdResBody> selectPrescriptionById(Long prescriptionId);

    /**
     * 根据患者编号查询我的处方
     *
     * @param reqBody
     * @return
     */
    Message<MyPageInfo<PrescriptionByUserIdResBody>> selectPrescriptionList(PrescriptionListReqBody reqBody);

}

