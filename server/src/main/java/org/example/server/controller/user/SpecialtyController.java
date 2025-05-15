package org.example.server.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.example.common.result.Result;

import org.example.pojo.entity.Specialty;
import org.example.server.server.impl.SpecialtyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user/specialty")
@Slf4j
@RestController("userSpecialtyController")
public class SpecialtyController {

    @Autowired
    private SpecialtyServiceImpl specialtyService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/list")
    @SuppressWarnings("unchecked")
    public Result<List<Specialty>> list() {
        String cachekey = "specialty:list";
        List<Specialty> specialtyList = (List<Specialty>) redisTemplate.opsForValue().get(cachekey);
        if (specialtyList != null) {
            //如果存在，则直接返回无需查询mysql
            log.info("从redis中获取数据");
            return Result.success(specialtyList);
        }
        log.info("从mysql中获取数据并存入redis");
        specialtyList = specialtyService.getAllSpecialty();
        redisTemplate.opsForValue().set(cachekey, specialtyList);
        return Result.success(specialtyList);
    }

    @GetMapping("/price/asc")
    @SuppressWarnings("unchecked")
    public Result<List<Specialty>> ByType() {
        String cachekey = "specialty:price/asc";
        List<Specialty> specialtyList = (List<Specialty>) redisTemplate.opsForValue().get(cachekey);
        if (specialtyList != null) {
            //如果存在，则直接返回无需查询mysql
            log.info("从redis中获取数据");
            return Result.success(specialtyList);
        }
        log.info("从mysql中获取数据并存入redis");
        specialtyList = specialtyService.ByPriceAsc();
        redisTemplate.opsForValue().set(cachekey, specialtyList);
        return Result.success(specialtyList);
    }

    @GetMapping("/price/desc")
    @SuppressWarnings("unchecked")
    public Result<List<Specialty>> ByPriceDesc() {
        String cachekey = "specialty:price/desc";
        List<Specialty> specialtyList = (List<Specialty>) redisTemplate.opsForValue().get(cachekey);
        if (specialtyList != null) {
            //如果存在，则直接返回无需查询mysql
            log.info("从redis中获取数据");
            return Result.success(specialtyList);
        }
        log.info("从mysql中获取数据并存入redis");
        specialtyList = specialtyService.ByPriceDesc();
        redisTemplate.opsForValue().set(cachekey, specialtyList);
        return Result.success(specialtyList);
    }

    @GetMapping("/sale")
    @SuppressWarnings("unchecked")
    public Result<List<Specialty>> BySale() {
        String cachekey = "specialty:sale";
        List<Specialty> specialtyList = (List<Specialty>) redisTemplate.opsForValue().get(cachekey);
        if (specialtyList != null) {
            //如果存在，则直接返回无需查询mysql
            log.info("从redis中获取数据");
            return Result.success(specialtyList);
        }
        log.info("从mysql中获取数据并存入redis");
        specialtyList = specialtyService.BySale();
        redisTemplate.opsForValue().set(cachekey, specialtyList);
        return Result.success(specialtyList);
    }

    @GetMapping("/type")
    @SuppressWarnings("unchecked")
    public Result<List<Specialty>> ByType(@RequestParam String type) {
        String cachekey = "specialty:type"+type;
        List<Specialty> specialtyList = (List<Specialty>) redisTemplate.opsForValue().get(cachekey);
        if (specialtyList != null) {
            //如果存在，则直接返回无需查询mysql
            log.info("从redis中获取数据");
            return Result.success(specialtyList);
        }
        log.info("从mysql中获取数据并存入redis");
        specialtyList = specialtyService.ByType(type);
        redisTemplate.opsForValue().set(cachekey, specialtyList);
        return Result.success(specialtyList);
    }

    @GetMapping("/search")
    public Result<List<Specialty>> search(@RequestParam String name) {
        List<Specialty> specialtyList = specialtyService.search(name);
        return Result.success(specialtyList);
    }

    @GetMapping("/detail")
    public Result<Specialty> getById(@RequestParam int id) {
        Specialty specialty = specialtyService.getSpecialtyById(id);
        return Result.success(specialty);
    }
}
