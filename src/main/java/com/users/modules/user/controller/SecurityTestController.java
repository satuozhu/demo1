package com.users.modules.user.controller;


import com.users.component.config.aspect.annotation.LogForController;
import com.users.modules.user.entity.CrUser;
import com.users.modules.mapper.primary.user.CrUserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Slf4j
@CrossOrigin(origins = "*")
@Api(description = "Security测试接口")
public class SecurityTestController {

    @Autowired
    private CrUserMapper crUserDAO;

    @PreAuthorize("hasAuthority('ADMIN')")
//    @ApiIgnore // 屏蔽（内部使用，不对外）
    @LogForController
    @ApiOperation(value = "查询用户UserId=1的个人信息")
    @RequestMapping(value = "/findCrUserBy", method = RequestMethod.GET)
    public CrUser findCrUserByUserId() {
        log.info("查询用户UserId=1的个人信息");
        CrUser crUser = crUserDAO.selectByPrimaryKey(1L);
        return crUser;
    }

}
