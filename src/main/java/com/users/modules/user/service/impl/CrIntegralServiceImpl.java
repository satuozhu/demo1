package com.users.modules.user.service.impl;

import com.common.entity.user.*;
import com.github.pagehelper.PageHelper;
import com.users.component.entity.Message;
import com.users.component.entity.MyPageInfo;
import com.users.component.entity.STATUS;
import com.users.component.util.IdGenerator;
import com.users.modules.mapper.primary.user.AppointmentMapper;
import com.users.modules.mapper.primary.user.CrIntegralDetailMapper;
import com.users.modules.mapper.primary.user.CrIntegralMapper;
import com.users.modules.mapper.primary.user.CrSysDictMapper;
import com.users.modules.user.requestBody.crAppointment.AddCrIntegralDetailReqBody;
import com.users.modules.user.requestBody.crAppointment.CrIntegralDetailListReqBody;
import com.users.modules.user.responseBody.crAppointment.CrIntegralDetailResBody;
import com.users.modules.user.service.ICrIntegralService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 积分service接口实现
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "Cache", keyGenerator = "myKeyGenerator")
public class CrIntegralServiceImpl implements ICrIntegralService {

    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private CrIntegralMapper crIntegralMapper;
    @Autowired
    private CrIntegralDetailMapper crIntegralDetailMapper;
    @Autowired
    private CrSysDictMapper crSysDictMapper;
    @Autowired
    private AppointmentMapper appointmentMapper;

    /**
     * 添加积分记录
     *
     * @param reqBody
     * @return
     */
    @Override
    public Message<?> addCrIntegralDetail(AddCrIntegralDetailReqBody reqBody) {
        try {
            CrSysDictExample csdExample = new CrSysDictExample();
            csdExample.createCriteria().andTypeCodeBetween("1000", "1100");
            List<CrSysDict> listCsd = crSysDictMapper.selectByExample(csdExample);//【1】先查询字典表
            for (CrSysDict crSysDict : listCsd) {
                if (crSysDict.getTypeCode().equals(reqBody.getType())) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    long now = System.currentTimeMillis();
                    int score = Integer.parseInt(crSysDict.getValue1());

                    CrIntegralDetail cid = new CrIntegralDetail();
                    cid.setId(idGenerator.nextId());
                    cid.setUserId(reqBody.getUserId());
                    cid.setDictType(reqBody.getType());
                    cid.setDictName(crSysDict.getName());
                    cid.setScore(score);

                    StringBuilder sb = new StringBuilder();
                    sb.append("时间：").append(sdf.format(now));
                    sb.append("，用户：").append(reqBody.getUserName());
                    sb.append("，获取").append(score).append("积分");
                    sb.append("，来源：").append(crSysDict.getName());
                    sb.append("，来源对象：");
                    if (StringUtils.isBlank(reqBody.getSourceObject())) {
                        sb.append("系统");
                    } else {
                        sb.append(reqBody.getSourceObject());
                    }

                    cid.setDetail(sb.toString());
                    cid.setCreateTime(now);
                    int num = crIntegralDetailMapper.insertSelective(cid);//【2】在添加积分详情
                    if (num > 0) {
                        //【3】用户积分（有则更新，无则添加）
                        appointmentMapper.insertOrUpdateCrIntegral(reqBody.getUserId(), score);
                        return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
                    }
                }
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 分页查询最近30天的积分明细
     *
     * @param reqBody
     * @return
     */
    @Cacheable
//    @Cacheable(value = {"Cache#5#2"},key ="#reqBody.userId + '_' + #p1 + '_' + #p2")
//    @Cacheable(value = "Cache#5#2", key ="#reqBody.userId + '_' + #p1 + '_' + #p2")
    @Override
    public Message<MyPageInfo<CrIntegralDetailResBody>> selectCrIntegralDetailList(CrIntegralDetailListReqBody reqBody) {
        try {
            PageHelper.startPage(reqBody.getPageNum(), reqBody.getPageSize());// 分页参数
            List<CrIntegralDetailResBody> list = appointmentMapper.selectCrIntegralDetailList(reqBody);
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, new MyPageInfo<>(list));
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 根据用户ID查询用户的积分
     *
     * @param userId
     * @return
     */
    @Cacheable
    @Override
    public Message<CrIntegral> selectCrIntegralByUserId(Long userId) {
        try {
            CrIntegralExample example = new CrIntegralExample();
            example.createCriteria().andUserIdEqualTo(userId);
            List<CrIntegral> list = crIntegralMapper.selectByExample(example);
            if (null != list && !list.isEmpty()) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, list.get(0));
            }
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

}