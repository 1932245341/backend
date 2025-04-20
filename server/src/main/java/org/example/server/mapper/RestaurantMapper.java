package org.example.server.mapper;

import com.github.pagehelper.Page;
import org.example.pojo.dto.PageQueryDTO;

import org.example.pojo.entity.Restaurant;

import java.util.List;

public interface RestaurantMapper {

     void insert(Restaurant restaurant);
     void deleteById(int id);
     void update(Restaurant restaurant);


    /**
     * 用户端查询所有的餐馆(存入redis)
     * @return
     */
    List<Restaurant> queryAllRestaurant();


    /**
     * 查询本商家的餐馆
     * @param markerId
     * @return
     */
    List<Restaurant> queryByMarkerId(int markerId);


    /**
     * 管理员端分页查询所有商家的餐馆
     * @param pageQueryDTO
     * @return
     */
    Page<Restaurant> pageQuery(PageQueryDTO pageQueryDTO);
}
