package org.example.server.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.example.common.context.BaseContext;
import org.example.common.result.PageResult;
import org.example.common.result.Result;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Dish;
import org.example.pojo.entity.Ticket;
import org.example.server.server.interfa.DishService;
import org.example.server.server.interfa.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/admin/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/page")
    public Result<PageResult> page(PageQueryDTO pageQueryDTO) {
        log.info("分页查询本景区的门票：{}", pageQueryDTO);
        PageResult pageResult = ticketService.queryPage(pageQueryDTO);
        log.info("分页查询结果：{}", pageResult);
        return Result.success(pageResult);
    }

    @PostMapping("/add")
    public Result<String> add(@RequestBody Ticket ticket) {
        log.info("添加门票：{}", ticket);
        String key = "ticket"+ticket.getScenicId();
        clearRedis(key);
        ticketService.insert(ticket);
        return Result.success();
    }

    @PutMapping
    public Result<String> update(@RequestBody Ticket ticket) {
        log.info("修改门票：{}", ticket);
        String key = "ticket"+ticket.getScenicId();
        clearRedis(key);
        ticketService.update(ticket);
        return Result.success();
    }

    @DeleteMapping
    public Result<String> deleteById(Integer id) {
        log.info("删除门票：{}", id);
        String key = "ticket*";
        clearRedis(key);
        ticketService.deleteById(id);
        return Result.success();
    }

    /**
     * 清理redis缓存数据
     * @param keys
     */
    private void clearRedis(String keys) {
        Set<String> cacheKeys = redisTemplate.keys(keys);
        redisTemplate.delete(cacheKeys);
    }
}
