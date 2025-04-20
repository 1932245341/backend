package org.example.server.controller.admin;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.example.common.result.PageResult;
import org.example.common.result.Result;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Parking;
import org.example.pojo.entity.Specialty;
import org.example.server.server.interfa.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/admin/specialty")
@Slf4j
@RestController("adminSpecialtyController")
public class SpecialtyController {

    @Autowired
    private SpecialtyService specialtyService;
    @Autowired
    RedisTemplate redisTemplate;


    /**
     * 特产分页
     * @param pageQueryDTO 分页查询参数
     * @return 分页结果
     */
    @GetMapping("/page")
    public Result<PageResult> page(@Parameter PageQueryDTO pageQueryDTO) {
        log.info("特产分页查询：{}", pageQueryDTO);
        PageResult pageResult = specialtyService.queryPage(pageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Specialty specialty) {
        log.info("添加特产：{}", specialty);
        specialtyService.addSpecialty(specialty);
        //清理缓存数据
        String key = "specialty:*";
        clearRedis(key);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<Specialty> update(@RequestBody Specialty specialty) {
        log.info("修改特产：{}", specialty);
        specialtyService.updateSpecialty(specialty);
        //清理缓存数据
        String key = "specialty:*";
        clearRedis(key);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<String> deleteById(@PathVariable("id") Integer id) {
        log.info("删除特产：{}", id);
        specialtyService.deleteSpecialty(id);
        //清理缓存数据
        String key = "specialty:*";
        clearRedis(key);
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
