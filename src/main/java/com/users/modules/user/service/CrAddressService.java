package com.users.modules.user.service;

import com.common.entity.user.CrAddress;
import com.users.component.entity.AreaCascader;
import com.users.component.entity.Message;
import com.users.modules.user.requestBody.crAddress.AddAddressReqBody;
import com.users.modules.user.requestBody.crAddress.UpdateAddressReqBody;
import com.users.modules.user.requestBody.crAddress.UpdateDefaultAddressReqBody;

import java.util.List;

/**
 * 地址service接口
 */
public interface CrAddressService {

    /**
     * 添加收货地址
     * @param reqBody
     * @return
     */
    Message<?> addAddress(AddAddressReqBody reqBody);

    /**
     * 查询当前用户的所有收货地址
     * @param userId
     * @return
     */
    Message<List<CrAddress>> selectAddressByUserId(Long userId);

    /**
     * 根据地址ID查询收货地址
     * @param addressId
     * @return
     */
    Message<CrAddress> selectAddressByAddressId(Long addressId);

    /**
     * 根据地址ID删除收货地址
     * @param addressId
     * @return
     */
    Message<?> deleteAddressByAddressId(Long addressId);

    /**
     * 根据地址ID修改收货地址
     * @param reqBody
     * @return
     */
    Message<?> updateAddressByAddressId(UpdateAddressReqBody reqBody);

    /**
     * 修改为默认地址
     * @param reqBody
     * @return
     */
    Message<?> updateDefaultAddress(UpdateDefaultAddressReqBody reqBody);

    /**
     * 获取所有省市区,组织为树状
     *
     * @return
     */
    AreaCascader MenuArea();

}

