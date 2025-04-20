package org.example.server.server.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.common.context.BaseContext;
import org.example.common.result.PageResult;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Restaurant;

import org.example.server.mapper.RestaurantMapper;
import org.example.server.server.interfa.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Override
    public void insert(Restaurant restaurant) {
        restaurantMapper.insert(restaurant);
    }

    @Override
    public void update(Restaurant restaurant) {
        restaurantMapper.update(restaurant);
    }

    @Override
    public void deleteById(int id) {
        restaurantMapper.deleteById(id);
    }

    @Override
    public List<Restaurant> queryAllRestaurant() {
        return restaurantMapper.queryAllRestaurant();
    }

    @Override
    public List<Restaurant> queryByMarkerId(int markerId) {
        return restaurantMapper.queryByMarkerId(markerId);
    }


    /**
     * 管理员端分页查询所有餐馆
     * @param pageQueryDTO
     * @return
     */
    @Override
    public PageResult queryPage(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
        Page<Restaurant> page = restaurantMapper.pageQuery(pageQueryDTO);
        long total = page.getTotal();
        List<Restaurant> records = page.getResult();
        return new PageResult(total,records);
    }


}
