package org.example.server.controller.marketer;

import lombok.extern.slf4j.Slf4j;
import org.example.common.context.BaseContext;
import org.example.common.result.PageResult;
import org.example.common.result.Result;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Dish;
import org.example.server.server.interfa.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/marketer/dish")
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/page")
    public Result<PageResult> page(PageQueryDTO pageQueryDTO) {
        log.info("分页查询本餐馆的菜品：{}", pageQueryDTO);
        PageResult pageResult = dishService.queryPage(pageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/add")
    public Result<String> add(@RequestBody Dish dish) {
        log.info("添加菜品：{}", dish);
        String key = "dish"+dish.getRestaurantId();
        clearRedis(key);
        dishService.insert(dish);
        return Result.success();
    }

    @PutMapping
    public Result<String> update(@RequestBody Dish dish) {
        log.info("修改菜品：{}", dish);
        String key = "dish"+dish.getRestaurantId();
        clearRedis(key);
        dishService.update(dish);
        return Result.success();
    }

    @DeleteMapping
    public Result<String> deleteById(Integer id) {
        log.info("删除菜品：{}", id);
        String key = "dish*";
        clearRedis(key);
        dishService.deleteById(id);
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
