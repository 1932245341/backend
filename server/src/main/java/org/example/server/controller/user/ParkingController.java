package org.example.server.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.example.common.result.PageResult;
import org.example.common.result.Result;

import org.example.pojo.entity.Parking;
import org.example.server.server.interfa.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("userParkingController")
@RequestMapping("/user/parking")

@Slf4j
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/list")
    @SuppressWarnings("unchecked")
    public Result<List<Parking>> list() {
        String cachekey = "parking:list";
        List<Parking> parkingList = (List<Parking>) redisTemplate.opsForValue().get(cachekey);
        if (parkingList != null) {
            //如果存在，则直接返回无需查询mysql
            log.info("从redis中获取数据");
            return Result.success(parkingList);
        }
        log.info("从mysql中获取数据并存入redis");
        parkingList = parkingService.getAllParking();
        redisTemplate.opsForValue().set(cachekey, parkingList);
        return Result.success(parkingList);
    }
}