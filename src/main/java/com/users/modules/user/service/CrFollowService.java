package com.users.modules.user.service;

import com.users.modules.user.responseBody.CrFollowRespBody;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhujian
 * Date: 2019/5/30
 * Time: 14:32
 * Description: No Description
 */
public interface CrFollowService {

    /**
     * 通过ID查询关注医生的信息
     * @param followerId
     * @return
     */
    List<CrFollowRespBody> selectDoctorInfoByFollowerId(Long followerId);

    /**
     * 通过关注者id查询关注者实体id列表
     * @param followerId
     * @return
     */
    List<CrFollowRespBody> selectByFolloerId(Long followerId);
}
