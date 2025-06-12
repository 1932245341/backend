package org.example.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.entity.Admin;

import java.math.BigDecimal;

@Mapper
public interface AdminMapper {

    /**
     * 根据用户名查询员工(用于登录)
     * @param username
     * @return
     */
    @Select("select * from admin where username = #{username}")
    Admin getByUsername(String username);

    @Select("select count(*) from user")
    int getUserCount();

    @Select("SELECT SUM(total) FROM orders WHERE status = '已支付' AND specialty_id IS NOT NULL")
    BigDecimal sumSpecialtyTotal();

    @Select("SELECT SUM(total) FROM orders WHERE status = '已支付' AND scenic_id IS NOT NULL")
    BigDecimal sumScenicTotal();

    @Select("SELECT SUM(total) FROM orders WHERE status = '已支付' AND restaurant_id IS NOT NULL")
    BigDecimal sumRestaurantTotal();

    @Select("SELECT SUM(total) FROM orders WHERE status = '已支付' AND hotel_id IS NOT NULL")
    BigDecimal sumHotelTotal();

}
