package org.example.server.server.impl;

import cn.hutool.core.util.IdUtil;
import org.example.common.context.BaseContext;
import org.example.common.result.Result;
import org.example.pojo.entity.Order;
import org.example.server.mapper.OrderMapper;
import org.example.server.server.interfa.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
     public void addOrder(Order order) {
        order.setUserId(BaseContext.getCurrentId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        order.setCreateTime(LocalDateTime.now().format(formatter));
        order.setOrderNo(IdUtil.getSnowflakeNextIdStr());
        order.setStatus("待支付");
        orderMapper.insert(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderMapper.update(order);
    }

    @Override
    public void deleteOrder(int id) {
        orderMapper.deleteById(id);
    }

    @Override
    public List<Order> getByUserId(Order order) {
      return orderMapper.ByUserId(order);
    }

    @Override
    public List<Order> getByMarketerId(int id) {
        return orderMapper.ByMarketerId(id);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderMapper.selectAll();
    }

    @Override
    public Order selectByOrderNo(String orderNo) {
        return orderMapper.selectByOrderNo(orderNo);
    }

}
