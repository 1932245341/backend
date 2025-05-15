package org.example.server.controller.common;

import org.example.common.result.Result;
import org.example.pojo.entity.Images;
import org.example.server.server.impl.ImagesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/common/images")
public class ImagesController {

    @Autowired
    private ImagesServiceImpl imagesService;

    @PostMapping("/insert")
    public void insert(Images images) {
        imagesService.insert(images);
    }

    @PostMapping("/delete")
    public void deleteById(int id) {
        imagesService.deleteById(id);
    }

    @GetMapping("/list")
    public Result<List<Images>> listById(@RequestParam(required = false) Integer RestaurantId,
                                                   @RequestParam(required = false) Integer HotelId,
                                                   @RequestParam(required = false) Integer ScenicId) {
        List<Images> images;
        if (RestaurantId != null) {
            images = imagesService.ByRestaurantId(RestaurantId);
        } else if (HotelId != null) {
            images = imagesService.ByHotelId(HotelId);
        } else if (ScenicId != null) {
            images = imagesService.ByScenicId(ScenicId);
        } else {
            // 可以根据需求处理没有参数的情况，这里简单返回空列表
            images = List.of();
        }
        return Result.success(images);
    }

    }
