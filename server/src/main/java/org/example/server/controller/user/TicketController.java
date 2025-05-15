package org.example.server.controller.user;


import lombok.extern.slf4j.Slf4j;
import org.example.common.result.Result;
import org.example.pojo.entity.Dish;
import org.example.pojo.entity.Ticket;
import org.example.server.server.impl.DishServiceImpl;
import org.example.server.server.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController("userTicketController")
@RequestMapping("/user/ticket")
public class TicketController {
    @Autowired
    private TicketServiceImpl ticketService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/list")
    @SuppressWarnings("unchecked")
    public Result<List<Ticket>> list(long scenicId) {
        String cachekey = "ticket:"+scenicId;
        List<Ticket> ticketList = (List<Ticket>) redisTemplate.opsForValue().get(cachekey);
        if (ticketList != null) {
            //如果存在，则直接返回无需查询mysql
            log.info("从redis中获取数据");
            return Result.success(ticketList);
        }
        log.info("从mysql中获取数据并存入redis");
        ticketList = ticketService.queryByScenicId(scenicId);
        redisTemplate.opsForValue().set(cachekey, ticketList);
        return Result.success(ticketList);
    }

    @GetMapping("/detail")
    public Result<Ticket> queryById(Integer id) {
        Ticket ticket = ticketService.queryById(id);
        return Result.success(ticket);
    }

}
