package org.example.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.entity.Order;

import java.util.List;

@Mapper
public interface OrderMapper {

    void insert(Order order);
    void deleteById(int id);
    void update(Order order);
    List<Order> selectAll();
    List<Order> ByUserId(Order order);
    List<Order> ByMarketerId(int marketerId);

    @Select("select * from orders where order_no = #{orderNo}")
    Order selectByOrderNo(String orderNo);
}
