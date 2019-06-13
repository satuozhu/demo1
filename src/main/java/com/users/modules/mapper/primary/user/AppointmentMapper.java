package com.users.modules.mapper.primary.user;

import com.common.entity.user.CrAppointmentTime;
import com.common.entity.user.CrSysMessage;
import com.users.modules.user.requestBody.crAppointment.*;
import com.users.modules.user.responseBody.crAppointment.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AppointmentMapper {

    /**
     * 查询推送的消息
     *
     * @param reqBody
     * @return
     */
    List<CrSysMessage> selectCrSysMessageList(CrSysMessageListReqBody reqBody);

    /**
     * 更新消息状态为已读
     *
     * @param reqBody
     * @return
     */
    int updateCrSysMessage(UpdateCrSysMessageReqBody reqBody);

    /**
     * 根据医生ID查询预约信息
     *
     * @param doctorId
     * @return
     */
    List<AppointmentByDoctorIdResBody> selectAppointmentByDoctorId(@Param("doctorId") Long doctorId);

    /**
     * 用户积分（有则更新，无则添加）
     *
     * @param userId
     * @param score
     * @return
     */
    int insertOrUpdateCrIntegral(@Param("userId") Long userId, @Param("score") Integer score);

    /**
     * 分页查询最近30天的积分明细
     *
     * @param reqBody
     * @return
     */
    List<CrIntegralDetailResBody> selectCrIntegralDetailList(CrIntegralDetailListReqBody reqBody);

    /**
     * 医生好评率top3
     *
     * @returndoctorName
     */
    List<SelectDoctorInfoTopResBody> selectDoctorInfoTop();

    /**
     * 根据医生编号查询详情
     * @return
     */
    SelectDoctorInfoTopResBody selectDoctorInfoById(Long userId);


    /**
     * 搜索医生列表
     *
     * @param reqBody
     * @returndoctorName
     */
    List<SearchDoctorInfoResBody> searchDoctorInfoList(SearchDoctorInfoReqBody reqBody);

    /**
     * 查询正常运转的诊室（医生-诊室-时间段）
     *
     * @param reqBody
     * @return
     */
    List<SelectNormalHouseListResBody> selectNormalHouseList(NormalHouseListReqBody reqBody);

    /**
     * 查询正常运转的诊室（医生-时间段-诊室）
     *
     * @param reqBody
     * @return
     */
    List<SelectNormalHouseResBody> selectNormalHouse(NormalHouseReqBody reqBody);

    /**
     * 查询医生某一天的所有预约时间（医生-时间段-诊室）
     *
     * @param map
     * @return
     */
    List<CrAppointmentTime> selectDoctorAppointmentTime(Map<String, Object> map);

    /**
     * 查询医生和诊室某一天的所有预约时间（医生-诊室-时间段）
     *
     * @param map
     * @return
     */
    List<DoctorHouseTimeResBody> selectDoctorHouseTime(Map<String, Object> map);

    /**
     * 查看用户在该日期、时间段是否已有预约
     * @param map
     * @return
     */
    int selectWhetherAppointment(Map<String, Object> map);

//    /**
//     * 查看用户在该日期、时间段是否已有复诊
//     * @param map
//     * @return
//     */
//    int selectWhetherAppointmentAgain(Map<String, Object> map);

    /**
     * 根据预约ID查看预约信息
     *
     * @param appointmentId
     * @return
     */
    AppointmentByIdResBody selectAppointmentById(Long appointmentId);

    /**
     * 用户查看预约列表
     *
     * @param reqBody
     * @return
     */
//    AppointmentListResBody selectAppointmentOne(AppointmentListReqBody reqBody);
    List<AppointmentListResBody> selectAppointmentList(AppointmentListReqBody reqBody);

    /**
     * 用户查看历史预约列表
     *
     * @param reqBody
     * @return
     */
    List<AppointmentHistoryListResBody> selectAppointmentHistoryList(AppointmentHistoryListReqBody reqBody);

    /**
     * 根据医生编号查询医生信息
     *
     * @param doctorId
     * @return
     */
    DoctorInfoByIdResBody selectDoctorInfoByDoctorId(Long doctorId);

    /**
     * 根据患者编号查询我的医生
     *
     * @param reqBody
     * @return
     */
    List<DoctorInfoByUserIdResBody> selectDoctorInfoList(DoctorInfoListReqBody reqBody);

    /**
     * 根据处方ID查询处方
     *
     * @param prescriptionId
     * @return
     */
    PrescriptionByIdResBody selectPrescriptionById(Long prescriptionId);

    /**
     * 根据患者编号查询我的处方
     *
     * @param reqBody
     * @return
     */
    List<PrescriptionByUserIdResBody> selectPrescriptionList(PrescriptionListReqBody reqBody);

}