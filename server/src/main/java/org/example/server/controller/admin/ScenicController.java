package org.example.server.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.example.common.result.PageResult;
import org.example.common.result.Result;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Restaurant;
import org.example.pojo.entity.Scenic;
import org.example.server.server.impl.ScenicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController("adminScenicController")
@RequestMapping("/admin/scenic")
public class ScenicController {

    @Autowired
    private ScenicServiceImpl scenicService;
    @Autowired
    RedisTemplate redisTemplate;


    @PutMapping
    public Result<Restaurant> update(@RequestBody Scenic scenic){
        log.info("修改景区信息：{}",scenic);
        String key = "scenic:list";
        clearRedis(key);
        scenicService.update(scenic);
        return Result.success();
    }

    @DeleteMapping
    public Result<String> deleteById(Integer id){
        log.info("删除景区信息：{}",id);
        String key = "scenic:list";
        clearRedis(key);
        scenicService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/add")
    public Result<Restaurant> add(@RequestBody Scenic scenic){
        log.info("添加景区信息：{}",scenic);
        String key = "scenic:list";
        clearRedis(key);
        scenicService.insert(scenic);
        return Result.success();
    }


     //管理员端的分页查询
    @GetMapping("/page")
    public Result<PageResult> page(PageQueryDTO pageQueryDTO) {
        log.info("分页查询景区：{}", pageQueryDTO);
        PageResult pageResult = scenicService.queryPage(pageQueryDTO);
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
