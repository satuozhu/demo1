package com.users.modules.pres.service.impl;

import com.common.entity.pres.CrPrescription;
import com.common.entity.pres.CrPrescriptionExample;
import com.users.modules.mapper.primary.pres.CrPrescriptionDAO;
import com.users.modules.mapper.primary.user.CrUserMapper;
import com.users.modules.pres.service.ICrPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiangchao on 2019/5/24.
 */
@Service
public class CrPrescriptionServiceImpl implements ICrPrescriptionService{

    @Autowired
    private CrPrescriptionDAO prescriptionDAO;
    @Autowired
    private CrUserMapper userMapper;
    /**
     * 根据患者编号查询所有处方信息
     */
    @Override
    public List<CrPrescription> getUserPres(Long userId) {
        CrPrescriptionExample example = new CrPrescriptionExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<CrPrescription> list = prescriptionDAO.selectByExample(example);
        for (int i =0;i<list.size();i++){
            CrPrescription item = list.get(i);
            list.get(i).setDoctor(userMapper.selectByPrimaryKey2(item.getDoctorId()));
        }
        return list;
    }

    /**
     * 根据处方编号,查询处方详细信息
     */
    @Override
    public CrPrescription getPresDetails(Long presId) {
        CrPrescriptionExample example = new CrPrescriptionExample();
        example.createCriteria().andIdEqualTo(presId);
        CrPrescription prescription = prescriptionDAO.selectByExample(example).get(0);
        return prescription;
    }
}
