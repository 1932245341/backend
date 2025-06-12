package org.example.server.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.example.common.result.PageResult;
import org.example.common.result.Result;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Parking;
import org.example.server.server.interfa.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/admin/parking")
@Tag(name = "停车场相关接口", description = "管理停车场信息") // 替换 @Api
@Slf4j
public class ParkingController {

    @Autowired
    private ParkingService parkingService;
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 停车场分页
     * @param pageQueryDTO 分页查询参数
     * @return 分页结果
     */
    @GetMapping("/page")
    @Operation(summary = "停车场分页查询", description = "根据条件分页查询停车场数据") // 替换 @ApiOperation
    public Result<PageResult> page(
            @Parameter(description = "分页查询参数") PageQueryDTO pageQueryDTO // 参数描述
    ) {
        log.info("停车场分页查询：{}", pageQueryDTO);
        PageResult pageResult = parkingService.queryPage(pageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/list")
    public Result<Object> list() {
        log.info("查询所有停车场");
        return Result.success(parkingService.getAllParking());
    }

    @PostMapping("/add")
    public Result add(@RequestBody Parking parking) {
        log.info("添加停车场：{}", parking);
        parkingService.addParking(parking);
        //清理缓存数据
        String key = "parking:list";
        clearRedis(key);
        return Result.success();
     }

     @PutMapping("/update")
     public Result<Parking> update(@RequestBody Parking parking) {
         log.info("修改停车场：{}", parking);
         parkingService.updateParking(parking);
         //清理缓存数据
         String key = "parking:list";
         clearRedis(key);
         return Result.success();
     }

    @DeleteMapping
    public Result<String> deleteById(Integer id){
        log.info("删除停车场信息：{}",id);
        String key = "parking:list";
        clearRedis(key);
        parkingService.deleteParking(id);
        return Result.success();
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