package com.users.modules.mapper.secondary;

import com.users.modules.user.responseBody.CrArticleRespBody;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhujian
 * Date: 2019/5/30
 * Time: 17:49
 * Description: No Description
 */
@Repository
public interface CrFollowArticleDAO {

    /**
     * 通过作者ID查询网文信息
     * @param articleAuthorId
     * @return
     */
    List<CrArticleRespBody> selectArticleByAuthorId(@Param("articleAuthorId") String articleAuthorId);
}
