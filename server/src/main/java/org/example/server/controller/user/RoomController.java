package org.example.server.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.example.common.result.Result;
import org.example.pojo.entity.Room;
import org.example.server.server.impl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController("userRoomController")
@RequestMapping("/user/room")
public class RoomController {
    @Autowired
    private RoomServiceImpl roomService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/list")
    @SuppressWarnings("unchecked")
    public Result<List<Room>> list(long hotel_id) {
        String cachekey = "room:"+hotel_id;
        List<Room> roomList = (List<Room>) redisTemplate.opsForValue().get(cachekey);
        if (roomList != null) {
            //如果存在，则直接返回无需查询mysql
            log.info("从redis中获取数据");
            return Result.success(roomList);
        }
        log.info("从mysql中获取数据并存入redis");
        roomList = roomService.queryByHotelId(hotel_id);
        redisTemplate.opsForValue().set(cachekey, roomList);
        return Result.success(roomList);
    }

    @GetMapping("/detail")
    public Result<Room> getById(Integer id) {
        Room room = roomService.getById(id);
        return Result.success(room);
    }

}
