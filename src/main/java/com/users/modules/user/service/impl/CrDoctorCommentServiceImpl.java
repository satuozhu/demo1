package com.users.modules.user.service.impl;

import com.common.entity.user.CrDoctorComment;
import com.common.entity.user.CrDoctorCommentExample;
import com.users.modules.mapper.primary.user.CrDoctorCommentDAO;
import com.users.modules.mapper.primary.user.CrUserMapper;
import com.users.modules.user.service.ICrDoctorCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiangchao on 2019/5/27.
 */
@Service
public class CrDoctorCommentServiceImpl implements ICrDoctorCommentService {
    @Autowired
    private CrDoctorCommentDAO doctorCommentDAO;
    @Autowired
    private CrUserMapper userMapper;
    /**
     * 根据医生编号查询评论信息
     *
     * @param id
     * @return
     */
    @Override
    public List<CrDoctorComment> queryCommentsById(Long id) {
        CrDoctorCommentExample example = new CrDoctorCommentExample();
        example.setOrderByClause("create_time desc");
        example.createCriteria().andDoctorIdEqualTo(id);
        List<CrDoctorComment> list = doctorCommentDAO.selectByExample(example);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setAuthor(userMapper.selectByPrimaryKey2(list.get(i).getAuthorId()));
        }
        return list;
    }


    /**
     * 根据用户ID查询用户给医生的留言信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<CrDoctorComment> queryCommentsByUserId(Long userId) {
        CrDoctorCommentExample example = new CrDoctorCommentExample();
        example.setOrderByClause("create_time desc");
        example.createCriteria().andAuthorIdEqualTo(userId);
        List<CrDoctorComment> list = doctorCommentDAO.selectByExample(example);
        for (int i = 0; i < list.size(); i++) {
            CrDoctorComment item = list.get(i);
            //设置被留言用户信息
            list.get(i).setDoctor(userMapper.selectByPrimaryKey2(item.getDoctorId()));
            //判断是否有回复留言
            CrDoctorCommentExample example2 = new CrDoctorCommentExample();
            example2.createCriteria().andParentIdEqualTo(item.getId());
            List<CrDoctorComment> list2 = doctorCommentDAO.selectByExample(example2);
            for (int j = 0; j < list2.size(); j++) {
                list2.get(j).setAuthor(userMapper.selectByPrimaryKey2(list2.get(j).getAuthorId()));
            }
            if (list2!=null && list2.size()!=0){
                for (int z=0;z<list2.size();z++){
                    list2.get(z).setAuthor(userMapper.selectByPrimaryKey2(list2.get(z).getAuthorId()));
                }
                list.get(i).setChildren(list2);
            }
        }
        return list;
    }
}
