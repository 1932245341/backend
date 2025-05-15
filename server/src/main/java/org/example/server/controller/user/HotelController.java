package org.example.server.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.example.common.result.Result;
import org.example.pojo.entity.Hotel;
import org.example.server.server.impl.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController("userHotelController")
@RequestMapping("/user/hotel")
public class HotelController {

    @Autowired
    private HotelServiceImpl hotelService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    //平台严选
    @GetMapping("/list")
    @SuppressWarnings("unchecked")
    public Result<List<Hotel>> list() {
        String cachekey = "hotel:平台严选";
        List<Hotel> hotelList = (List<Hotel>) redisTemplate.opsForValue().get(cachekey);
        if (hotelList != null) {
            //如果存在，则直接返回无需查询mysql
            log.info("从redis中获取数据");
            return Result.success(hotelList);
        }
        log.info("从mysql中获取数据并存入redis");
        hotelList = hotelService.queryAllHotel();
        redisTemplate.opsForValue().set(cachekey, hotelList);
        return Result.success(hotelList);
    }

    @GetMapping("/byminprice")
    @SuppressWarnings("unchecked")
    public Result<List<Hotel>> byminprice() {
        String cachekey = "hotel:价格优先";
        List<Hotel> hotelList = (List<Hotel>) redisTemplate.opsForValue().get(cachekey);
        if (hotelList != null) {
            //如果存在，则直接返回无需查询mysql
            log.info("从redis中获取数据");
            return Result.success(hotelList);
        }
        log.info("从mysql中获取数据并存入redis");
        hotelList = hotelService.queryByprice();
        redisTemplate.opsForValue().set(cachekey, hotelList);
        return Result.success(hotelList);
    }

    @PostMapping("/userquery")
    public Result<List<Hotel>> userquery(@RequestBody Hotel hotel) {
        List<Hotel> hotelList = hotelService.userquery(hotel);
        return Result.success(hotelList);
    }

    @GetMapping("/detail")
    public Result<Hotel> detail(@RequestParam int id) {
        Hotel hotel = hotelService.queryById(id);
        return Result.success(hotel);
    }
}
