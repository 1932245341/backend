package org.example.server.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.example.common.result.PageResult;
import org.example.common.result.Result;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Hotel;
import org.example.pojo.entity.Restaurant;
import org.example.server.server.impl.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController("adminHotel")
@RequestMapping("/admin/hotel")
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


     //管理员端的分页查询
    @GetMapping("/page")
    public Result<PageResult> page(PageQueryDTO pageQueryDTO) {
        log.info("分页查询民宿：{}", pageQueryDTO);
        PageResult pageResult = hotelService.queryPage(pageQueryDTO);
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
