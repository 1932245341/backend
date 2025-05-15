package org.example.server.controller.user;

import org.example.common.context.BaseContext;
import org.example.common.result.Result;
import org.example.pojo.entity.Order;
import org.example.server.server.interfa.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //个人全部订单
  @GetMapping("/list")
  public Result<List<Order>> getByUserId() {
      Order order = new Order();
      order.setUserId(BaseContext.getCurrentId());
      List<Order> list = orderService.getByUserId(order);
      return Result.success(list);
  }

    @GetMapping("/payed")
    public Result<List<Order>> getByUserIdPayed() {
        Order order = new Order();
        order.setUserId(BaseContext.getCurrentId());
        order.setStatus("已支付");
        List<Order> list = orderService.getByUserId(order);
        return Result.success(list);
    }

    @GetMapping("/unpay")
    public Result<List<Order>> getByUserIdUnpayed() {
        Order order = new Order();
        order.setUserId(BaseContext.getCurrentId());
        order.setStatus("待支付");
        List<Order> list = orderService.getByUserId(order);
        return Result.success(list);
    }

    @PostMapping("/add")
    public Result addOrder(@RequestBody  Order order) {
        orderService.addOrder(order);
        return Result.success(order);
    }

    @PutMapping
    public Result updateOrder(@RequestBody Order order) {
        orderService.updateOrder(order);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteOrder(int id) {
        orderService.deleteOrder(id);
        return Result.success();
    }

}
