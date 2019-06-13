package com.users.modules.role.service;

import com.common.entity.role.CrSysModule;

import java.util.List;

/**
 * Created by xiangchao on 2019/5/20.
 */
public interface ISysModuleService {
    /**
     * 根据List<Integer>  id 查询Module,并返回Tree状结构
     */
    CrSysModule getModuleList(List<Long> ids);
}
