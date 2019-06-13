package com.users.modules.user.controller;

import com.users.component.entity.Message;
import com.users.component.entity.STATUS;
import com.users.modules.user.responseBody.CrFollowRespBody;
import com.users.modules.user.service.CrFollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhujian
 * Date: 2019/5/30
 * Time: 18:28
 * Description: No Description
 */

@RestController
@RequestMapping("/user/follow")
@Api(value = "CrFollowController",description = "关注---收藏")
public class CrFollowController {

    @Autowired
    private CrFollowService crFollowService;

    /**
     * 通过ID查询关注医生的信息+网文信息=关注
     * @param followerId
     * @return
     */
    @ApiOperation(value = "通过userId查询关注医生的信息+网文信息=关注")
    @RequestMapping(value = "/selectDoctorInfoByFollowerId/{followerId}",method = {RequestMethod.POST})
    public Message selectDoctorInfoByFollowerId(@PathVariable @ApiParam(value = "查询条件:userId") Long followerId){
        Message message = new Message();
        List<CrFollowRespBody> crFollowRespBodies = crFollowService.selectDoctorInfoByFollowerId(followerId);
        message.setData(crFollowRespBodies);

        message.setCode(STATUS.CODE_SUCCESS);
        message.setMsg("请求成功");
        return message;
    }

    /**
     * 通过关注者id查询关注者实体id列表+网文信息=收藏
     * @param followerId
     * @return
     */
    @ApiOperation(value = "通过userId查询关注者实体id列表+网文信息=收藏")
    @RequestMapping(value ="/selectByFolloerId/{followerId}",method = {RequestMethod.POST})
    public Message selectByFolloerId(@PathVariable @ApiParam(value = "查询条件:userId") Long followerId){
        Message message = new Message();
        List<CrFollowRespBody> crFollowRespBodies = crFollowService.selectByFolloerId(followerId);
        message.setData(crFollowRespBodies);
        message.setCode(STATUS.CODE_SUCCESS);
        message.setMsg("请求成功");
        return message;
    }
}
