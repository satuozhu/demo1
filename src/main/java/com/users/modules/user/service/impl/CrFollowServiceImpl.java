package com.users.modules.user.service.impl;

import com.users.modules.mapper.primary.user.CrFollowMapper;
import com.users.modules.mapper.secondary.CrFollowArticleDAO;
import com.users.modules.user.responseBody.CrArticleRespBody;
import com.users.modules.user.responseBody.CrFollowRespBody;
import com.users.modules.user.service.CrFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhujian
 * Date: 2019/5/30
 * Time: 14:32
 * Description: No Description
 */
@Service
public class CrFollowServiceImpl implements CrFollowService {

    @Autowired
    private CrFollowMapper crFollowMapper;

    @Autowired
    private CrFollowArticleDAO crFollowArticleDAO;

    /**
     * 通过ID查询关注医生信息+医生的网文信息
     * @param followerId
     * @return
     */
    @Override
    public List<CrFollowRespBody> selectDoctorInfoByFollowerId(Long followerId) {

        List<CrFollowRespBody> crFollowRespBodies = crFollowMapper.selectDoctorInfoByFollowerId(followerId);

        for (CrFollowRespBody crFollowRespBody: crFollowRespBodies) {

            List<CrArticleRespBody> crArticleRespBodys = crFollowArticleDAO.selectArticleByAuthorId(Long.toString(crFollowRespBody.getFollowingId()));

            crFollowRespBody.setCrArticleRespBody(crArticleRespBodys);

        }

        return crFollowRespBodies;
    }

    /**
     * 通过关注者id查询关注者实体id列表+网文信息
     * @param followerId
     * @return
     */
    @Override
    public List<CrFollowRespBody> selectByFolloerId(Long followerId) {

        List<CrFollowRespBody> crFollowRespBodies = crFollowMapper.selectByFolloerId(followerId);

        for (CrFollowRespBody crFollowRespBody:crFollowRespBodies) {

            List<CrArticleRespBody> crArticleRespBodys = crFollowArticleDAO.selectArticleByAuthorId(Long.toString(crFollowRespBody.getFollowingId()));

            crFollowRespBody.setCrArticleRespBody(crArticleRespBodys);
        }

        return crFollowRespBodies;
    }
}
