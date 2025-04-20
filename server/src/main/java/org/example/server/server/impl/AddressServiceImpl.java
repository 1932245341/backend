package org.example.server.server.impl;

import org.example.common.context.BaseContext;
import org.example.pojo.entity.Address;
import org.example.server.mapper.AddressMapper;
import org.example.server.server.interfa.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    /**
     * 条件查询
     *
     * @param address
     * @return
     */
    public List<Address> list(Address address) {
        return addressMapper.list(address);
    }

    /**
     * 新增地址
     *
     * @param address
     */
    public void save(Address address) {
        address.setUserId(BaseContext.getCurrentId());
        address.setIsDefault(0);
        addressMapper.insert(address);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public Address getById(Long id) {
        Address address = addressMapper.getById(id);
        return address;
    }

    /**
     * 根据id修改地址
     *
     * @param address
     */
    public void update(Address address) {
        addressMapper.update(address);
    }

    /**
     * 设置默认地址
     *
     * @param address
     */
    @Transactional
    public void setDefault(Address address) {
        // 1、将当前用户的所有地址修改为非默认地址 update address set is_default = ? where user_id = ?
        address.setIsDefault(0);
        address.setUserId(BaseContext.getCurrentId());
        addressMapper.updateIsDefaultByUserId(address);

        // 2、将当前地址改为默认地址 update address set is_default = ? where id = ?
        address.setIsDefault(1);
        addressMapper.update(address);
    }

    /**
     * 根据id删除地址
     *
     * @param id
     */
    public void deleteById(Long id) {
        addressMapper.deleteById(id);
    }

}
