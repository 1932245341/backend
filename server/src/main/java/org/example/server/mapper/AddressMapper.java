package org.example.server.mapper;


import org.apache.ibatis.annotations.*;
import org.example.pojo.entity.Address;

import java.util.List;

@Mapper
public interface AddressMapper {

    /**
     * 条件查询
     * @param address
     * @return
     */
    List<Address> list(Address address);

    /**
     * 新增
     * @param address
     */
    @Insert("insert into address" +
            "        (user_id, consignee, phone, sex, province_code, province_name, city_code, city_name, district_code," +
            "         district_name, detail, label, is_default)" +
            "        values (#{userId}, #{consignee}, #{phone}, #{sex}, #{provinceCode}, #{provinceName}, #{cityCode}, #{cityName}," +
            "                #{districtCode}, #{districtName}, #{detail}, #{label}, #{isDefault})")
    void insert(Address address);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from address where id = #{id}")
    Address getById(Long id);

    /**
     * 根据id修改
     * @param address
     */
    void update(Address address);

    /**
     * 根据 用户id修改 是否默认地址
     * @param address
     */
    @Update("update address set is_default = #{isDefault} where user_id = #{userId}")
    void updateIsDefaultByUserId(Address address);

    /**
     * 根据id删除地址
     * @param id
     */
    @Delete("delete from address where id = #{id}")
    void deleteById(Long id);

}
