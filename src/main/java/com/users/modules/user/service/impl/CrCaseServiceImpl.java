package com.users.modules.user.service.impl;

import com.common.entity.user.CrCase;
import com.common.entity.user.CrCaseExample;
import com.users.modules.mapper.primary.user.CrCaseMapper;
import com.users.modules.user.service.ICrCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiangchao on 2019/5/27.
 */
@Service
public class CrCaseServiceImpl implements ICrCaseService {

    @Autowired
    private CrCaseMapper caseDAO;

    /**
     * 根据医生编号查询case
     * @param id
     * @return
     */
    @Override
    public List<CrCase> getCaseByDoctorId(Long id) {
        CrCaseExample example =new CrCaseExample();
        example.createCriteria().andDoctorIdEqualTo(id);
        return caseDAO.selectByExample(example);
    }
}
