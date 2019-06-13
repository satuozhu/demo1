package com.users.modules.role.controller;

import com.common.entity.role.CrSysModule;
import com.users.modules.role.service.ISysModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangchao on 2019/5/20.
 */
@RestController
@RequestMapping("/role")
@Slf4j
@CrossOrigin(origins = "*")
@Api(description = "权限接口")
public class CrSysRoleController {
    @Autowired
    private ISysModuleService sysModule;
    @ApiOperation("获取所有权限菜单")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public CrSysModule getList(){
        return sysModule.getModuleList(null);
    }
}
