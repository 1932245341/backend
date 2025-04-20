package org.example.server.controller.marketer;

import lombok.extern.slf4j.Slf4j;
import org.example.common.result.PageResult;
import org.example.common.result.Result;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Restaurant;
import org.example.server.server.impl.RestaurantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController("marketer")
@RequestMapping("/marketer/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantServiceImpl restaurantService;
    @Autowired
    RedisTemplate redisTemplate;


    @PutMapping
    public Result<Restaurant> update(@RequestBody Restaurant restaurant){
        log.info("修改餐馆信息：{}",restaurant);
        String key = "restaurant:list";
        clearRedis(key);
        restaurantService.update(restaurant);
        return Result.success();
    }

    @DeleteMapping
    public Result<String> deleteById(Integer id){
        log.info("删除餐馆信息：{}",id);
        String key = "restaurant:list";
        clearRedis(key);
        restaurantService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/add")
    public Result<Restaurant> add(@RequestBody Restaurant restaurant){
        log.info("添加餐馆信息：{}",restaurant);
        String key = "restaurant:list";
        clearRedis(key);
        restaurantService.insert(restaurant);
        return Result.success();
    }


     //管理员端的分页查询
    @GetMapping("/page")
    public Result<PageResult> page(PageQueryDTO pageQueryDTO) {
        log.info("分页查询餐馆：{}", pageQueryDTO);
        PageResult pageResult = restaurantService.queryPage(pageQueryDTO);
        return Result.success(pageResult);
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
