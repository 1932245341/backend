package org.example.server.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.example.common.result.Result;
import org.example.pojo.entity.Scenic;
import org.example.server.server.impl.ScenicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController("userScenicController")
@RequestMapping("/user/scenic")
public class ScenicController {

    @Autowired
    private ScenicServiceImpl scenicService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/list")
    @SuppressWarnings("unchecked")
    public Result<List<Scenic>> list() {
        String cachekey = "scenic:list";
        List<Scenic> scenicList = (List<Scenic>) redisTemplate.opsForValue().get(cachekey);
        if (scenicList != null) {
            //如果存在，则直接返回无需查询mysql
            log.info("从redis中获取数据");
            return Result.success(scenicList);
        }
        log.info("从mysql中获取数据并存入redis");
        scenicList = scenicService.queryAllScenic();
        redisTemplate.opsForValue().set(cachekey, scenicList);
        return Result.success(scenicList);
    }

    @GetMapping("/recommend")
    @SuppressWarnings("unchecked")
    public Result<List<Scenic>> recommend() {
        String cachekey = "scenic:recommend";
        List<Scenic> scenicList = (List<Scenic>) redisTemplate.opsForValue().get(cachekey);
        if (scenicList != null) {
            //如果存在，则直接返回无需查询mysql
            log.info("从redis中获取数据");
            return Result.success(scenicList);
        }
        log.info("从mysql中获取数据并存入redis");
        scenicList = scenicService.recommendation();
        redisTemplate.opsForValue().set(cachekey, scenicList);
        return Result.success(scenicList);
    }

    @GetMapping("/detail")
    public Result<Scenic> detail(int id) {
        Scenic scenic = scenicService.queryById(id);
        return Result.success(scenic);
    }
}
