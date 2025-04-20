package org.example.server.controller.marketer;

import lombok.extern.slf4j.Slf4j;
import org.example.common.context.BaseContext;
import org.example.common.result.PageResult;
import org.example.common.result.Result;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Dish;
import org.example.pojo.entity.Room;
import org.example.server.server.interfa.DishService;
import org.example.server.server.interfa.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/marketer/room")
public class RoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/page")
    public Result<PageResult> page(PageQueryDTO pageQueryDTO) {
        log.info("分页查询本民宿的套房：{}", pageQueryDTO);
        PageResult pageResult = roomService.queryPage(pageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/add")
    public Result<String> add(@RequestBody Room room) {
        log.info("添加套房：{}", room);
        String key = "room:*";
        clearRedis(key);
        roomService.insert(room);
        return Result.success();
    }

    @PutMapping
    public Result<String> update(@RequestBody Room room) {
        log.info("修改套房：{}", room);
        String key = "room:*";
        clearRedis(key);
        roomService.update(room);
        return Result.success();
    }

    @DeleteMapping
    public Result<String> deleteById(Integer id) {
        log.info("删除套房：{}", id);
        String key = "room:*";
        clearRedis(key);
        roomService.deleteById(id);
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
