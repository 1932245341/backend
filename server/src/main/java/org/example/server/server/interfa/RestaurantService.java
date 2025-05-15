package org.example.server.server.interfa;

import org.example.common.result.PageResult;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Restaurant;

import java.util.List;

public interface RestaurantService {

    void insert(Restaurant restaurant);
    void update(Restaurant restaurant);
    void deleteById(int id);
    List<Restaurant> queryAllRestaurant();
    PageResult queryPage(PageQueryDTO pageQueryDTO);
    List<Restaurant> queryByMarkerId(int markerId);
    Restaurant queryById(int id);
}
