package com.users.modules.role.service.impl;

import com.common.entity.role.CrSysModule;
import com.common.entity.role.CrSysModuleExample;
import com.users.modules.mapper.primary.role.CrSysModuleDAO;
import com.users.modules.role.service.ISysModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangchao on 2019/5/20.
 */
@Service
public class SysModuleServiceImpl implements ISysModuleService {
    @Autowired
    private CrSysModuleDAO moduleDAO;

    /**
     * 根据List<Integer>  id 查询Module,并返回Tree状结构
     * @param ids
     * @return
     */
    @Override
    public CrSysModule getModuleList(List<Long> ids) {
        //组织条件
        CrSysModuleExample example = new CrSysModuleExample();
        if (ids!=null&&ids.size()>0){
            example.createCriteria().andIdIn(ids);
        }
        //数据查询
        List<CrSysModule> list = moduleDAO.selectByExample(example);
        //条件判断
        if (list != null && list.size() != 0) {
            /**
             *Tree生成
             */
            //根节点
            CrSysModule root = new CrSysModule();
            root.setId(0L);
            root.setModulename("根节点");
            return deep(list, root);
        }
        return null;
    }

    private CrSysModule deep(List<CrSysModule> list, CrSysModule root) {
        //便利list,查询parentId为rootId的节点
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getParentid().equals(root.getId().toString())) {
                if (root.getChildren() == null) {
                    root.setChildren(new ArrayList<CrSysModule>());
                }
                root.getChildren().add(deep(list, list.get(i)));
                list.remove(i);
                i--;
            }
        }
        return root;
    }
}
