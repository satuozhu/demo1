package com.users.modules.user.service;

import com.common.entity.user.CrDoctorComment;

import java.util.List;

/**
 * Created by xiangchao on 2019/5/27.
 */
public interface ICrDoctorCommentService {
    /**
     * 根据医生id获取留言信息
     * @param id
     * @return
     */
    List<CrDoctorComment> queryCommentsById(Long id);

    /**
     * 根据用户ID查询用户给医生的留言信息
     * @param userId
     * @return
     */
    List<CrDoctorComment> queryCommentsByUserId(Long userId);

}
