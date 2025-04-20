package org.example.server.controller.user;


import lombok.extern.slf4j.Slf4j;
import org.example.common.result.Result;
import org.example.pojo.entity.Dish;

import org.example.server.server.impl.DishServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController("userDishController")
@RequestMapping("/user/dish")
public class DishController {
    @Autowired
    private DishServiceImpl dishService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/list")
    @SuppressWarnings("unchecked")
    public Result<List<Dish>> list(long restaurant_id) {
        String cachekey = "dish:"+restaurant_id;
        List<Dish> dishList = (List<Dish>) redisTemplate.opsForValue().get(cachekey);
        if (dishList != null) {
            //如果存在，则直接返回无需查询mysql
            log.info("从redis中获取数据");
            return Result.success(dishList);
        }
        log.info("从mysql中获取数据并存入redis");
        dishList = dishService.queryByRestaurantId(restaurant_id);
        redisTemplate.opsForValue().set(cachekey, dishList);
        return Result.success(dishList);
    }

}
