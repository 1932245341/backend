package org.example.server.controller.marketer;

import lombok.extern.slf4j.Slf4j;
import org.example.common.context.BaseContext;
import org.example.common.result.PageResult;
import org.example.common.result.Result;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Hotel;
import org.example.pojo.entity.Restaurant;
import org.example.server.server.impl.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/marketer/hotel")
public class HotelController {

    @Autowired
    private HotelServiceImpl hotelService;
    @Autowired
    RedisTemplate redisTemplate;


    @PutMapping
    public Result<Restaurant> update(@RequestBody Hotel hotel){
        log.info("修改民宿信息：{}",hotel);
        String key = "hotel*";
        clearRedis(key);
        hotelService.update(hotel);
        return Result.success();
    }

    @DeleteMapping
    public Result<String> deleteById(Integer id){
        log.info("删除民宿信息：{}",id);
        String key = "hotel*";
        clearRedis(key);
        hotelService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/add")
    public Result<Restaurant> add(@RequestBody Hotel hotel){
        log.info("添加民宿信息：{}",hotel);
        String key = "hotel*";
        clearRedis(key);
        hotelService.insert(hotel);
        return Result.success();
    }



    @GetMapping("list")
    public Result<List> list(){
        List<Hotel> list = hotelService.queryByMarkerId(BaseContext.getCurrentId());
        return Result.success(list);
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
