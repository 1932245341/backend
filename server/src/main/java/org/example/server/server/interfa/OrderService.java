package org.example.server.server.interfa;

import org.example.pojo.entity.Order;

import java.util.List;

public interface OrderService {

     void addOrder(Order order);

     void updateOrder(Order order);

     void deleteOrder(int id);

    List<Order> getAllOrder();

    List<Order> getByMarketerId(int marketerId);

     List<Order> getByUserId(Order order);

     Order selectByOrderNo(String orderNo);

}
