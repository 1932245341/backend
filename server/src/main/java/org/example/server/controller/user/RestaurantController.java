package org.example.server.controller.user;

import lombok.extern.slf4j.Slf4j;

import org.example.common.result.Result;

import org.example.pojo.entity.Restaurant;
import org.example.server.server.impl.RestaurantServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController("user")
@RequestMapping("/user/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantServiceImpl restaurantService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/list")
    @SuppressWarnings("unchecked")
    public Result<List<Restaurant>> list() {
        String cachekey = "restaurant:list";
        List<Restaurant> restaurantList = (List<Restaurant>) redisTemplate.opsForValue().get(cachekey);
        if (restaurantList != null) {
            //如果存在，则直接返回无需查询mysql
            log.info("从redis中获取数据");
            return Result.success(restaurantList);
        }
        log.info("从mysql中获取数据并存入redis");
        restaurantList = restaurantService.queryAllRestaurant();
        redisTemplate.opsForValue().set(cachekey, restaurantList);
        return Result.success(restaurantList);
    }
}
