package com.users.modules.user.service.impl;

import com.users.component.entity.AreaCascader;
import com.users.component.entity.Message;
import com.users.component.entity.STATUS;
import com.users.component.util.IdGenerator;
import com.common.entity.user.*;
import com.users.modules.mapper.primary.user.CrAddressMapper;
import com.users.modules.mapper.primary.user.CrSysAreaMapper;
import com.users.modules.user.requestBody.crAddress.AddAddressReqBody;
import com.users.modules.user.requestBody.crAddress.UpdateAddressReqBody;
import com.users.modules.user.requestBody.crAddress.UpdateDefaultAddressReqBody;
import com.users.modules.user.service.CrAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 地址service接口实现
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "Cache", keyGenerator = "myKeyGenerator")
public class CrAddressServiceImpl implements CrAddressService {

    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private CrAddressMapper crAddressMapper;
    @Autowired
    private CrSysAreaMapper crSysAreaMapper;

    /**
     * 添加收货地址
     *
     * @param reqBody
     * @return
     */
    @Override
    public Message<?> addAddress(AddAddressReqBody reqBody) {
        try {
            CrAddress crAddress = new CrAddress();
            crAddress.setId(idGenerator.nextId());
            crAddress.setAreaId(reqBody.getAreaId());
            crAddress.setStreet(reqBody.getStreet());
            crAddress.setDetailAddress(reqBody.getDetailAddress());
            crAddress.setUserId(reqBody.getUserId());
            crAddress.setConcacts(reqBody.getConcacts());
            crAddress.setPhone(reqBody.getPhone());
            crAddress.setDefaultAddress(reqBody.getDefaultAddress()==1?true:false);//默认地址（0否、1是）
            if(reqBody.getDefaultAddress() == 1){//true
                //若添加为默认地址，则需把其他的默认地址去掉
                CrAddress ca = new CrAddress();
                ca.setDefaultAddress(false);
                CrAddressExample example = new CrAddressExample();
                CrAddressExample.Criteria c = example.createCriteria();
                c.andUserIdEqualTo(reqBody.getUserId());
                c.andDefaultAddressEqualTo(true);
                crAddressMapper.updateByExampleSelective(ca, example);
            }
            if (crAddressMapper.insertSelective(crAddress) > 0) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 查询当前用户的所有收货地址
     *
     * @param userId
     * @return
     */
    @Cacheable
    @Override
    public Message<List<CrAddress>> selectAddressByUserId(Long userId) {
        try {
            CrAddressExample example = new CrAddressExample();
            example.createCriteria().andUserIdEqualTo(userId);
            List<CrAddress> list = crAddressMapper.selectByExample(example);
            if (null != list && !list.isEmpty()) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, list);
            }
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 根据地址ID查询收货地址
     *
     * @param addressId
     * @return
     */
    @Cacheable
    @Override
    public Message<CrAddress> selectAddressByAddressId(Long addressId) {
        try {
            CrAddress crAddress = crAddressMapper.selectByPrimaryKey(addressId);
            if (null != crAddress) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, crAddress);
            }
            return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 根据地址ID删除收货地址
     *
     * @param addressId
     * @return
     */
    @Override
    public Message<?> deleteAddressByAddressId(Long addressId) {
        try {
            int num = crAddressMapper.deleteByPrimaryKey(addressId);
            if (num > 0) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 根据地址ID修改收货地址
     *
     * @param reqBody
     * @return
     */
    @Override
    public Message<?> updateAddressByAddressId(UpdateAddressReqBody reqBody) {
        try {
            CrAddress crAddress = new CrAddress();
            crAddress.setId(reqBody.getId());
            crAddress.setAreaId(reqBody.getAreaId());
            crAddress.setStreet(reqBody.getStreet());
            crAddress.setDetailAddress(reqBody.getDetailAddress());
            //crAddress.setUserId(reqBody.getUserId());
            crAddress.setConcacts(reqBody.getConcacts());
            crAddress.setPhone(reqBody.getPhone());
            crAddress.setDefaultAddress(reqBody.getDefaultAddress()==1?true:false);//默认地址（0否、1是）
            if(reqBody.getDefaultAddress() == 1){//true
                //若修改为默认地址，则需把其他的默认地址去掉
                CrAddress ca = new CrAddress();
                ca.setDefaultAddress(false);
                CrAddressExample example = new CrAddressExample();
                CrAddressExample.Criteria c = example.createCriteria();
                c.andIdNotEqualTo(reqBody.getId());
                c.andUserIdEqualTo(reqBody.getUserId());
                c.andDefaultAddressEqualTo(true);
                crAddressMapper.updateByExampleSelective(ca, example);
            }
            if (crAddressMapper.updateByPrimaryKeySelective(crAddress) > 0) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 修改为默认地址
     *
     * @param reqBody
     * @return
     */
    @Override
    public Message<?> updateDefaultAddress(UpdateDefaultAddressReqBody reqBody) {
        try {
            CrAddress crAddress = new CrAddress();
            crAddress.setId(reqBody.getId());
            crAddress.setDefaultAddress(reqBody.getDefaultAddress()==1?true:false);//默认地址（0否、1是）
            if(reqBody.getDefaultAddress() == 1){//true
                //若修改为默认地址，则需把其他的默认地址去掉
                CrAddress ca = new CrAddress();
                ca.setDefaultAddress(false);
                CrAddressExample example = new CrAddressExample();
                CrAddressExample.Criteria c = example.createCriteria();
                c.andIdNotEqualTo(reqBody.getId());
                c.andUserIdEqualTo(reqBody.getUserId());
                c.andDefaultAddressEqualTo(true);
                crAddressMapper.updateByExampleSelective(ca, example);
            }
            if (crAddressMapper.updateByPrimaryKeySelective(crAddress) > 0) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, true);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    /**
     * 获取所有省市区,组织为树状
     *
     * @return
     */
    @Cacheable(value = "selectAreas")
    @Override
    public AreaCascader MenuArea() {
        //1:查询所有地区信息
        List<CrSysArea> areaList = crSysAreaMapper.selectByExample(new CrSysAreaExample());
        //2:设置root节点
        AreaCascader root = new AreaCascader(0, "---请选择---", null, false);
        AreaCascader menus = deep(root, areaList);
        return menus;
    }

    /**
     * 递归组织关系
     *
     * @return
     */
    private AreaCascader deep(AreaCascader parentElement, List<CrSysArea> list) {
        //1:list中查询所有parent_id为parentElement的value
        for (int i = 0; i < list.size(); i++) {
            //2:获取子项,判断pid是否相等
            CrSysArea item = list.get(i);
            if (parentElement.getValue().equals(Integer.parseInt(item.getParentCode()))) {
                if (parentElement.getChildren() == null) {
                    parentElement.setChildren(new ArrayList<AreaCascader>());
                }
                //3:创建新 menu,在list中删除 item
                AreaCascader menuItem = new AreaCascader(Integer.parseInt(item.getAreaCode()), item.getAreaName(), null, false);
                list.remove(i);
                i--;
                //4:递归查询新menu子项,新增子节点
                menuItem = deep(menuItem, list);
                parentElement.getChildren().add(menuItem);
            }
        }
        return parentElement;
    }

}

