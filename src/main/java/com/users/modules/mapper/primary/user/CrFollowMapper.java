package com.users.modules.mapper.primary.user;

import com.users.modules.user.responseBody.CrFollowRespBody;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhujian
 * Date: 2019/5/30
 * Time: 17:04
 * Description: No Description
 */
@Repository
public interface CrFollowMapper {

    /**
     * 通过ID查询关注医生的信息
     * @param followerId
     * @return
     */
    List<CrFollowRespBody> selectDoctorInfoByFollowerId(@Param("followerId") Long followerId);

    /**
     * 通过关注者id查询关注者实体id列表
     * @param followerId
     * @return
     */
    List<CrFollowRespBody> selectByFolloerId(@Param("followerId") Long followerId);


}
