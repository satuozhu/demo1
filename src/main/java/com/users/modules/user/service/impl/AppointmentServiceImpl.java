package com.users.modules.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.common.entity.user.*;
import com.github.pagehelper.PageHelper;
import com.users.component.entity.Message;
import com.users.component.entity.MyPageInfo;
import com.users.component.entity.STATUS;
import com.users.component.util.IdGenerator;
import com.users.modules.mapper.primary.user.*;
import com.users.modules.user.requestBody.crAppointment.*;
import com.users.modules.user.responseBody.crAppointment.*;
import com.users.modules.user.service.AppointmentService;
import com.users.modules.user.service.ICrIntegralService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 预约service接口实现
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "Cache", keyGenerator = "myKeyGenerator")
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private CrAppointmentTimeMapper crAppointmentTimeMapper;
    @Autowired
    private CrAppointmentMapper crAppointmentMapper;
    @Autowired
    private CrCaseMapper crCaseMapper;
    @Autowired
    private CrUserCertificationMapper crUserCertificationMapper;
    @Autowired
    private CrSysMessageDAO crSysMessageDAO;
    @Autowired
    private CrQuestionDAO crQuestionDAO;
    @Autowired
    private CrUserMapper userMapper;
    @Autowired
    private ICrIntegralService iCrIntegralService;
    @Autowired
    private CrHouseMapper houseMapper;
    /**
     * 查询最新的3条提问
     *
     * @return
     */
//    @Cacheable
    @Override
    public Message<List<CrQuestion>> selectCrQuestionTop(CrQuestionTopReqBody reqBody) {
        try {
            CrQuestionExample example = new CrQuestionExample();
            CrQuestionExample.Criteria c = example.createCriteria();
            c.andStatusEqualTo(true);//回复状态(0未回复、1已回复)
            if (!StringUtils.isBlank(reqBody.getContent())) {
                c.andContentLike("%" + reqBody.getContent() + "%");
            }
            example.setOrderByClause(" create_time desc ");//倒序查询
            example.setLimit(3);//top3
            List<CrQuestion> list = crQuestionDAO.selectByExample(example);
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, list);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 添加提问
     *
     * @param reqBody
     * @return
     */
    @Override
    public Message<?> addCrQuestion(AddCrQuestionReqBody reqBody) {
        try {
            long now = System.currentTimeMillis();
            CrQuestion crQuestion = new CrQuestion();
            crQuestion.setId(idGenerator.nextId());
            crQuestion.setType(reqBody.getType());
            crQuestion.setContent(reqBody.getContent());
            crQuestion.setUserId(reqBody.getUserId());
            crQuestion.setBeanId(reqBody.getBeanId());
//            crQuestion.setReplyContent("");
//            crQuestion.setStatus(false);//回复状态(0未回复、1已回复)
            crQuestion.setCreateTime(now);
            crQuestion.setUpdateTime(now);
            int num = crQuestionDAO.insertSelective(crQuestion);
            if (num > 0) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 回复提问
     *
     * @param reqBody
     * @return
     */
    @Override
    public Message<?> replyCrQuestion(ReplyCrQuestionReqBody reqBody) {
        try {
            long now = System.currentTimeMillis();
            CrQuestion crQuestion = new CrQuestion();
            crQuestion.setId(reqBody.getId());
            crQuestion.setReplyContent(reqBody.getReplyContent());
            crQuestion.setStatus(true);//回复状态(0未回复、1已回复)
            crQuestion.setUpdateTime(now);
            int num = crQuestionDAO.updateByPrimaryKeySelective(crQuestion);
            if (num > 0) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 查询推送的消息
     *
     * @return
     */
//    @Cacheable
    @Override
    public Message<MyPageInfo<CrSysMessage>> selectCrSysMessageList(CrSysMessageListReqBody reqBody) {
        try {
//            List<CrSysMessage> list = new ArrayList<>();//查询
//            boolean isTrue = redisUtils.exists("pushMessage");
//            if (isTrue) {
//                list = (List<CrSysMessage>) redisUtils.get("pushMessage");
//                //Collections.sort(list);// 顺序排列
//                //Collections.shuffle(list);// 混乱排列
//                //Collections.reverse(list);// 倒序排列
//            }

//            CrSysMessageExample example = new CrSysMessageExample();
//            example.setOrderByClause(" create_time desc ");//倒序查询
//            List<CrSysMessage> list = crSysMessageDAO.selectByExample(example);

            PageHelper.startPage(reqBody.getPageNum(), reqBody.getPageSize());// 分页参数
            List<CrSysMessage> list = appointmentMapper.selectCrSysMessageList(reqBody);
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, new MyPageInfo<>(list));
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 更新消息状态为已读
     *
     * @param reqBody
     * @return
     */
    @Override
    public Message<?> updateCrSysMessage(UpdateCrSysMessageReqBody reqBody) {
        try {
            CrSysMessage crSysMessage = new CrSysMessage();
            crSysMessage.setId(reqBody.getMessageId());
            crSysMessage.setMsgStatus(true);//消息状态(0未读、1已读)
            crSysMessage.setUpdateTime(System.currentTimeMillis());
            int num = crSysMessageDAO.updateByPrimaryKeySelective(crSysMessage);
//            int num = appointmentMapper.updateCrSysMessage(reqBody);
            if (num > 0) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 根据医生ID查询预约信息
     *
     * @param doctorId
     * @return
     */
    @Cacheable
    @Override
    public Message<List<AppointmentByDoctorIdResBody>> selectAppointmentByDoctorId(Long doctorId) {
        try {
            List<AppointmentByDoctorIdResBody> list = appointmentMapper.selectAppointmentByDoctorId(doctorId);
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, list);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 根据医生ID查询预约信息列表
     *
     * @param reqBody
     * @return
     */
    @Override
    public Message<MyPageInfo<AppointmentByDoctorIdResBody>> selectAppointmentByDoctorId2(AppointmentByDoctorIdReqBody reqBody) {
        try {
            PageHelper.startPage(reqBody.getPageNum(), reqBody.getPageSize());// 分页参数
            List<AppointmentByDoctorIdResBody> list = appointmentMapper.selectAppointmentByDoctorId(reqBody.getDoctorId());
            for (int i = 0; i < list.size(); i++) {
                AppointmentByDoctorIdResBody item = list.get(i);
                //查询用户信息
                list.get(i).setUser(userMapper.selectByPrimaryKey2(item.getUserId()));
                //查询预约时间段信息
                list.get(i).setAppointmentTime(crAppointmentTimeMapper.selectByPrimaryKey(item.getAppointmentTimeId()));
                //查询诊室信息
                list.get(i).setHouse(houseMapper.selectByPrimaryKey(item.getHouseId()));
            }
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, new MyPageInfo<>(list));
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 医生好评率top3
     *
     * @return
     */
    @Cacheable
    @Override
    public Message<List<SelectDoctorInfoTopResBody>> selectDoctorInfoTop() {
        try {
            List<SelectDoctorInfoTopResBody> list = appointmentMapper.selectDoctorInfoTop();
            List<String> list1 = new ArrayList<>();
            list1.add("中医世家");
            list1.add("好评如潮");
            list1.add("人气专家");
            for (SelectDoctorInfoTopResBody resBody : list) {
//                map.put("patientNum", (int)(Math.random()*1000));//问诊人数0-1000
//                map.put("praiseRate", (int)(Math.random()*10+90));//好评率90-100
//                map.put("tag", list1);//标签
                resBody.setTag(list1);
            }
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, list);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 根据医生Id查询医生信息和详细信息
     *
     * @return
     */
    @Override
    public SelectDoctorInfoTopResBody selectDoctorInfoById(Long userId) {
        SelectDoctorInfoTopResBody detail = appointmentMapper.selectDoctorInfoById(userId);
        return detail;
    }

    /**
     * 搜索医生列表
     *
     * @param reqBody
     * @return
     */
    @Cacheable(value = "Cache1")
    @Override
    public Message<MyPageInfo<SearchDoctorInfoResBody>> searchDoctorInfoList(SearchDoctorInfoReqBody reqBody) {
        try {
            PageHelper.startPage(reqBody.getPageNum(), reqBody.getPageSize());// 分页参数
            List<SearchDoctorInfoResBody> list = appointmentMapper.searchDoctorInfoList(reqBody);
            List<String> list1 = new ArrayList<>();
            list1.add("中医世家");
            list1.add("好评如潮");
            list1.add("人气专家");
            for (SearchDoctorInfoResBody resBody : list) {
//                map.put("patientNum", (int)(Math.random()*1000));//问诊人数0-1000
//                map.put("praiseRate", (int)(Math.random()*10+90));//好评率90-100
//                map.put("tag", list1);//标签
                resBody.setTag(list1);
            }
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, new MyPageInfo<>(list));
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 查询正常运转的诊室（医生-诊室-时间段）
     *
     * @param reqBody
     * @return
     */
    @Cacheable(value = "Cache2")
    @Override
    public Message<MyPageInfo<SelectNormalHouseListResBody>> selectNormalHouseList(NormalHouseListReqBody reqBody) {
        try {
            PageHelper.startPage(reqBody.getPageNum(), reqBody.getPageSize());// 分页参数
//            Map<String, Object> map = new HashMap<>();
//            map.put("houseName", reqBody.getHouseName());
//            map.put("areaId", reqBody.getAreaId());//这里是Long，csa.`parent_code`是String，会自动转，所以不影响
            List<SelectNormalHouseListResBody> list = appointmentMapper.selectNormalHouseList(reqBody);
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, new MyPageInfo<>(list));
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 查询正常运转的诊室（医生-时间段-诊室）
     *
     * @param reqBody
     * @return
     */
    @Cacheable
    @Override
    public Message<MyPageInfo<SelectNormalHouseResBody>> selectNormalHouse(NormalHouseReqBody reqBody) {
        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//yyyy-MM-dd HH:mm:ss
//            Map<String, Object> map = new HashMap<>();
//            map.put("houseName", reqBody.getHouseName());
//            map.put("areaId", reqBody.getAreaId());//这里是Long，csa.`parent_code`是String，会自动转，所以不影响
//            map.put("date", sdf.parse(reqBody.getDate()));
//            map.put("timeSlot", reqBody.getTimeSlot());
            PageHelper.startPage(reqBody.getPageNum(), reqBody.getPageSize());// 分页参数
            List<SelectNormalHouseResBody> list = appointmentMapper.selectNormalHouse(reqBody);
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, new MyPageInfo<>(list));
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 查询医生某一天的所有预约时间（医生-时间段-诊室）
     *
     * @param doctorId
     * @param date
     * @return
     */
    @Cacheable
    @Override
    public Message<List<CrAppointmentTime>> selectDoctorAppointmentTime(Long doctorId, String date) {
        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//yyyy-MM-dd HH:mm:ss
//            CrAppointmentTimeExample example = new CrAppointmentTimeExample();
////            CrUserExample.Criteria c = example.createCriteria();
//            example.createCriteria().andDoctorIdEqualTo(doctorId).andDateEqualTo(sdf.parse(date));//有时区问题应该加8个小时，或手写SQL
//            example.setOrderByClause(" time_slot ASC ");//时间段升序查询
//            List<CrAppointmentTime> list = crAppointmentTimeMapper.selectByExample(example);

            Map<String, Object> map = new HashMap<>();
            map.put("doctorId", doctorId);
            map.put("date", date);
            List<CrAppointmentTime> list = appointmentMapper.selectDoctorAppointmentTime(map);
            if (null != list && !list.isEmpty()) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, list);
            }
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 查询医生和诊室某一天的所有预约时间（医生-诊室-时间段）
     *
     * @param doctorId
     * @param houseId
     * @param date
     * @return
     */
    @Cacheable
    @Override
    public Message<List<DoctorHouseTimeResBody>> selectDoctorHouseTime(Long doctorId, Long houseId, String date) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("doctorId", doctorId);
            map.put("houseId", houseId);
            map.put("date", date);
            List<DoctorHouseTimeResBody> list = appointmentMapper.selectDoctorHouseTime(map);
            if (null != list && !list.isEmpty()) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, list);
            }
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 确认初诊预约
     *
     * @param reqBody
     * @return
     */
    @Override
    public Message<?> confirmAppointment(ConfirmAppointmentAgainReqBody reqBody) {
        try {
            //【1】初诊预约日期必须大于当前日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//yyyy-MM-dd HH:mm:ss
            if (sdf.parse(reqBody.getAppointmentDate()).compareTo(sdf.parse(sdf.format(new Date()))) <= 0) {
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, "初诊预约日期必须大于当前日期");
            }

            //【2】查看是否已实名认证
            CrUserCertificationExample example = new CrUserCertificationExample();
            example.createCriteria().andUserIdEqualTo(reqBody.getUserId());
            List<CrUserCertification> listCuc = crUserCertificationMapper.selectByExample(example);
            if (null == listCuc || listCuc.isEmpty()) {
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, "该用户未实名认证");
            }
            if (listCuc.get(0).getAuthStatus() != 2) {//认证状态：0未认证、1认证中、2认证通过、3认证失败
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, "该用户实名认证尚未通过");
            }

            //【3】查看用户在该日期、时间段是否已有预约
            Map<String, Object> map = new HashMap<>();
            map.put("userId", reqBody.getUserId());
            map.put("date", reqBody.getAppointmentDate());
            map.put("timeSlot", reqBody.getTimeSlot());
            int count = appointmentMapper.selectWhetherAppointment(map);
            if (count > 0) {
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, "该用户在日期" + reqBody.getAppointmentDate() + "、时间段" + reqBody.getTimeSlot() + "已有预约");
            }

//            //或看该用户是否已存在进行中123的预约
//            CrAppointmentExample caExample = new CrAppointmentExample();
//            List<String> list = new ArrayList<>();
//            list.add("1");
//            list.add("2");
//            list.add("3");
//            caExample.createCriteria().andUserIdEqualTo(reqBody.getUserId()).andStatusIn(list);
//            List<CrAppointment> listCa = crAppointmentMapper.selectByExample(caExample);
//            if(null != listCa && listCa.size() > 0){
//                return new Message<>(STATUS.CODE_PARAMETER_ERROR, "该用户存在未完成的预约");
//            }

            long now = System.currentTimeMillis();
            //【4】查看是否已被预约
            CrAppointmentTime cat = crAppointmentTimeMapper.selectByPrimaryKey(reqBody.getAppointmentTimeId());
            if (cat.getStatus()) {
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, "该时间段已被预约");
            }
            cat.setStatus(true);//是否已被预约（0false否 1true是）
            cat.setUpdateTime(now);
            //【5】更新时间段状态为已被预约
            if (crAppointmentTimeMapper.updateByPrimaryKeySelective(cat) > 0) {

                //【6】添加预约信息
                CrAppointment ca = new CrAppointment();
                long aid = idGenerator.nextId();
                ca.setId(aid);
                ca.setUserId(reqBody.getUserId());
                ca.setDoctorId(reqBody.getDoctorId());
                ca.setHouseId(reqBody.getHouseId());
                ca.setAppointmentTimeId(reqBody.getAppointmentTimeId());//预约时间编号
//                ca.setCause("");//预约原因
                ca.setConsultPrice(reqBody.getConsultPrice());
                ca.setStatus("2");//预约状态(0已取消、1待支付、2预约中、3就诊中、4配送中、5已完成)
//                ca.setPrescriptionId(0l);//处方编号(不用)
//                ca.setExpressId(0l);//物流编号
                ca.setSumPrice(reqBody.getConsultPrice());
//                ca.setScore(0);
//                ca.setExStatus(0);//物流状态 0 进行中 1 已完成 2 配送中
//                ca.setAgainStatus(false);//复诊状态(0=false否、1=true是)
//                ca.setAppointmentId(0l);//预约编号(0则是初诊,否则是复诊)
                ca.setDate(now);
                ca.setUpdateTime(now);
                int num = crAppointmentMapper.insertSelective(ca);
                if (num > 0) {
//                    JSONObject jsonObject = new JSONObject();
//                    jsonObject.put("id", idGenerator.nextId());
//                    jsonObject.put("msgType", 3);
//                    jsonObject.put("msgTitle", "初诊预约成功通知");
//                    jsonObject.put("msgContent", "恭喜"+reqBody.getUserName()+"，您已经成功预约"+reqBody.getDoctorName()+"，初诊预约单号" + ca.getId() +
//                            "，初诊预约时间"+reqBody.getAppointmentDate()+" "+reqBody.getTimeSlot()+"，初诊预约诊室"+reqBody.getHouseName());
//                    jsonObject.put("msgStatus", false);
//                    jsonObject.put("userId", reqBody.getUserId());
//                    jsonObject.put("beanId", aid);
//                    jsonObject.put("createTime", now);
//                    jsonObject.put("updateTime", now);
//                    kafkaTemplate.send("push_message", jsonObject.toString());

                    CrSysMessage csm = new CrSysMessage();
                    csm.setId(idGenerator.nextId());
                    csm.setMsgType(3);
                    csm.setMsgTitle("初诊预约成功通知");
                    csm.setMsgContent("恭喜" + reqBody.getUserName() + "，您已经成功预约" + reqBody.getDoctorName() + "，初诊预约单号" + ca.getId() +
                            "，初诊预约时间" + reqBody.getAppointmentDate() + " " + reqBody.getTimeSlot() + "，初诊预约诊室" + reqBody.getHouseName());
                    csm.setMsgStatus(false);//消息状态(0未读、1已读)
                    csm.setUserId(reqBody.getUserId());
                    csm.setBeanId(aid);
                    csm.setCreateTime(now);
                    csm.setUpdateTime(now);
                    if (crSysMessageDAO.insertSelective(csm) > 0) {
                        //添加积分
                        AddCrIntegralDetailReqBody acidReqBody = new AddCrIntegralDetailReqBody();
                        acidReqBody.setUserId(reqBody.getUserId());
                        acidReqBody.setUserName(reqBody.getUserName());
                        acidReqBody.setType("1006");
                        acidReqBody.setSourceObject("患者初诊预约");
                        iCrIntegralService.addCrIntegralDetail(acidReqBody);
                    }

                    JSONObject json = new JSONObject();
                    json.put("orderId", ca.getId());
                    return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, json);
                }
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 确认复诊预约
     *
     * @param reqBody
     * @return
     */
    @Override
    public Message<?> confirmAppointmentAgain(ConfirmAppointmentAgainReqBody reqBody) {
        try {
            //【1】复诊预约日期必须大于当前日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//yyyy-MM-dd HH:mm:ss
            if (sdf.parse(reqBody.getAppointmentDate()).compareTo(sdf.parse(sdf.format(new Date()))) <= 0) {
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, "复诊预约日期必须大于当前日期");
            }

//            //【2】查看是否已实名认证（一般预约的时候就已经判断过了）
//            CrUserCertificationExample example = new CrUserCertificationExample();
//            example.createCriteria().andUserIdEqualTo(reqBody.getUserId());
//            List<CrUserCertification> listCuc = crUserCertificationMapper.selectByExample(example);
//            if (null == listCuc || listCuc.isEmpty()) {
//                return new Message<>(STATUS.CODE_PARAMETER_ERROR, "该用户未实名认证");
//            }
//            if (listCuc.get(0).getAuthStatus() != 2) {//认证状态：0未认证、1认证中、2认证通过、3认证失败
//                return new Message<>(STATUS.CODE_PARAMETER_ERROR, "该用户实名认证尚未通过");
//            }

            //【3】查看用户在该日期、时间段是否已有预约
            Map<String, Object> map = new HashMap<>();
            map.put("userId", reqBody.getUserId());
            map.put("date", reqBody.getAppointmentDate());
            map.put("timeSlot", reqBody.getTimeSlot());
            int count = appointmentMapper.selectWhetherAppointment(map);
            if (count > 0) {
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, "该用户在日期" + reqBody.getAppointmentDate() + "、时间段" + reqBody.getTimeSlot() + "已有预约");
            }

//            //或看该用户是否已存在进行中123的复诊
//            CrAppointmentAgainExample caaExample = new CrAppointmentAgainExample();
//            List<String> list = new ArrayList<>();
//            list.add("1");
//            list.add("2");
//            list.add("3");
//            caaExample.createCriteria().andUserIdEqualTo(reqBody.getUserId()).andStatusIn(list);
//            List<CrAppointmentAgain> listCa = crAppointmentAgainMapper.selectByExample(caaExample);
//            if(null != listCa && listCa.size() > 0){
//                return new Message<>(STATUS.CODE_PARAMETER_ERROR, "该用户存在未完成的复诊");
//            }

            long now = System.currentTimeMillis();
            //【4】查看是否已被预约
            CrAppointmentTime cat = crAppointmentTimeMapper.selectByPrimaryKey(reqBody.getAppointmentTimeId());
            if (cat.getStatus()) {
                return new Message<>(STATUS.CODE_PARAMETER_ERROR, "该时间段已被预约");
            }
            cat.setStatus(true);//是否已被预约（0false否 1true是）
            cat.setUpdateTime(now);
            //【5】更新时间段状态为已被预约
            if (crAppointmentTimeMapper.updateByPrimaryKeySelective(cat) > 0) {

                //【6】添加预约信息
                CrAppointment ca = new CrAppointment();
                long aid = idGenerator.nextId();
                ca.setId(aid);
                ca.setUserId(reqBody.getUserId());
                ca.setDoctorId(reqBody.getDoctorId());
                ca.setHouseId(reqBody.getHouseId());
                ca.setAppointmentTimeId(reqBody.getAppointmentTimeId());//预约时间编号
//                ca.setCause("");//预约原因
                ca.setConsultPrice(reqBody.getConsultPrice());
                ca.setStatus("2");//预约状态(0已取消、1待支付、2预约中、3就诊中、4配送中、5已完成)
//                ca.setPrescriptionId(0l);//处方编号(不用)
//                ca.setExpressId(0l);//物流编号
                ca.setSumPrice(reqBody.getConsultPrice());
//                ca.setScore(0);
//                ca.setExStatus(0);//物流状态 0 进行中 1 已完成 2 配送中
//                ca.setAgainStatus(false);//复诊状态(0=false否、1=true是)
                ca.setAppointmentId(reqBody.getAppointmentId());//预约编号(0则是初诊,否则是复诊)
                ca.setDate(now);
                ca.setUpdateTime(now);
                int num = crAppointmentMapper.insertSelective(ca);
                if (num > 0) {
//                    JSONObject jsonObject = new JSONObject();
//                    jsonObject.put("id", idGenerator.nextId());
//                    jsonObject.put("msgType", 3);
//                    jsonObject.put("msgTitle", "复诊预约成功通知");
//                    jsonObject.put("msgContent", "恭喜"+reqBody.getUserName()+"，您已经成功预约"+reqBody.getDoctorName()+"，复诊预约单号" + ca.getId() +
//                            "，复诊预约时间"+reqBody.getAppointmentDate()+" "+reqBody.getTimeSlot()+"，复诊预约诊室"+reqBody.getHouseName());
//                    jsonObject.put("msgStatus", false);
//                    jsonObject.put("userId", reqBody.getUserId());
//                    jsonObject.put("beanId", aid);
//                    jsonObject.put("createTime", now);
//                    jsonObject.put("updateTime", now);
//                    kafkaTemplate.send("push_message", jsonObject.toString());

                    CrSysMessage csm = new CrSysMessage();
                    csm.setId(idGenerator.nextId());
                    csm.setMsgType(3);
                    csm.setMsgTitle("复诊预约成功通知");
                    csm.setMsgContent("恭喜" + reqBody.getUserName() + "，您已经成功预约" + reqBody.getDoctorName() + "，复诊预约单号" + ca.getId() +
                            "，复诊预约时间" + reqBody.getAppointmentDate() + " " + reqBody.getTimeSlot() + "，复诊预约诊室" + reqBody.getHouseName());
                    csm.setMsgStatus(false);//消息状态(0未读、1已读)
                    csm.setUserId(reqBody.getUserId());
                    csm.setBeanId(aid);
                    csm.setCreateTime(now);
                    csm.setUpdateTime(now);
                    if (crSysMessageDAO.insertSelective(csm) > 0) {
                        //添加积分
                        AddCrIntegralDetailReqBody acidReqBody = new AddCrIntegralDetailReqBody();
                        acidReqBody.setUserId(reqBody.getUserId());
                        acidReqBody.setUserName(reqBody.getUserName());
                        acidReqBody.setType("1006");
                        acidReqBody.setSourceObject("患者复诊预约");
                        iCrIntegralService.addCrIntegralDetail(acidReqBody);
                    }

                    JSONObject json = new JSONObject();
                    json.put("orderId", ca.getId());
                    return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, json);
                }
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 根据预约ID查看预约信息
     *
     * @param appointmentId
     * @return
     */
    @Cacheable
    @Override
    public Message<AppointmentByIdResBody> selectAppointmentById(Long appointmentId) {
        try {
            AppointmentByIdResBody resBody = appointmentMapper.selectAppointmentById(appointmentId);
            if (null != resBody) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, resBody);
            }
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 用户查看预约列表
     *
     * @param reqBody
     * @return
     */
//    @Cacheable
//    @Override
//    public Message<AppointmentListResBody> selectAppointmentOne(AppointmentListReqBody reqBody) {
//        try {
//            AppointmentListResBody list = appointmentMapper.selectAppointmentOne(reqBody);
//            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, list);
//        } catch (Exception e) {
//            log.error("程序出错", e);
//        }
//        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
//    }
    @Cacheable
    @Override
    public List<AppointmentListResBody> selectAppointmentList(AppointmentListReqBody reqBody) {
        return appointmentMapper.selectAppointmentList(reqBody);
    }
//      //不可以这样写（报自引用错误）
//    @Cacheable
//    @Override
//    public Message<MyPageInfo<AppointmentListResBody>> selectAppointmentList(AppointmentListReqBody reqBody) {
//        try {
//            PageHelper.startPage(reqBody.getPageNum(), reqBody.getPageSize());// 分页参数
//            List<AppointmentListResBody> listResBody = appointmentMapper.selectAppointmentList(reqBody);
//            for (AppointmentListResBody al : listResBody) {
//                al.setAppointment(al);//不可以这样写（报自引用错误）
//            }
//            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, new MyPageInfo<>(listResBody));
//        } catch (Exception e) {
//            log.error("程序出错", e);
//        }
//        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
//    }

    /**
     * 用户查看历史预约列表
     *
     * @param reqBody
     * @return
     */
    @Cacheable
    @Override
    public Message<MyPageInfo<AppointmentHistoryListResBody>> selectAppointmentHistoryList(AppointmentHistoryListReqBody reqBody) {
        try {
            PageHelper.startPage(reqBody.getPageNum(), reqBody.getPageSize());// 分页参数
            List<AppointmentHistoryListResBody> list = appointmentMapper.selectAppointmentHistoryList(reqBody);
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, new MyPageInfo<>(list));
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 查询医生经典案例
     *
     * @param reqBody
     * @return
     */
    @Cacheable
    @Override
    public Message<MyPageInfo<CrCase>> selectDoctorCaseList(DoctorCaseReqBody reqBody) {
        try {
            PageHelper.startPage(reqBody.getPageNum(), reqBody.getPageSize());// 分页参数
            CrCaseExample example = new CrCaseExample();
            example.createCriteria().andDoctorIdEqualTo(reqBody.getDoctorId());
            example.setOrderByClause(" create_time DESC ");//创建时间降序查询
            List<CrCase> list = crCaseMapper.selectByExample(example);
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, new MyPageInfo<>(list));
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 根据医生编号查询医生信息
     *
     * @param doctorId
     * @return
     */
    @Cacheable
    @Override
    public Message<DoctorInfoByIdResBody> selectDoctorInfoByDoctorId(Long doctorId) {
        try {
            DoctorInfoByIdResBody map = appointmentMapper.selectDoctorInfoByDoctorId(doctorId);
            if (null != map) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, map);
            }
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 根据患者编号查询我的医生
     *
     * @param reqBody
     * @return
     */
    @Cacheable
    @Override
    public Message<MyPageInfo<DoctorInfoByUserIdResBody>> selectDoctorInfoList(DoctorInfoListReqBody reqBody) {
        try {
            PageHelper.startPage(reqBody.getPageNum(), reqBody.getPageSize());// 分页参数
            List<DoctorInfoByUserIdResBody> list = appointmentMapper.selectDoctorInfoList(reqBody);
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, new MyPageInfo<>(list));
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 根据处方ID查询处方
     *
     * @param prescriptionId
     * @return
     */
    @Cacheable
    @Override
    public Message<PrescriptionByIdResBody> selectPrescriptionById(Long prescriptionId) {
        try {
//            CrPrescription crPrescription = crPrescriptionMapper.selectByPrimaryKey(prescriptionId);
            PrescriptionByIdResBody map = appointmentMapper.selectPrescriptionById(prescriptionId);
            if (null != map) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, map);
            }
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 根据患者编号查询我的处方
     *
     * @param reqBody
     * @return
     */
    @Cacheable
    @Override
    public Message<MyPageInfo<PrescriptionByUserIdResBody>> selectPrescriptionList(PrescriptionListReqBody reqBody) {
        try {
            PageHelper.startPage(reqBody.getPageNum(), reqBody.getPageSize());// 分页参数
            List<PrescriptionByUserIdResBody> list = appointmentMapper.selectPrescriptionList(reqBody);
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, new MyPageInfo<>(list));
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

}

