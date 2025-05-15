package org.example.server.server.impl;

import org.example.pojo.entity.Images;
import org.example.server.mapper.ImagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagesServiceImpl {

    @Autowired
    private ImagesMapper imagesMapper;

    public void insert(Images images) {
        imagesMapper.insert(images);
    }

    public void deleteById(int id) {
        imagesMapper.deleteById(id);
    }

    public List<Images> ByRestaurantId(int RestaurantId) {
        return imagesMapper.ByRestaurantId(RestaurantId);
    }

    public List<Images> ByHotelId(int HotelId) {
        return imagesMapper.ByHotelId(HotelId);
    }

    public List<Images> ByScenicId(int ScenicId) {
        return imagesMapper.ByScenicId(ScenicId);
    }


}
