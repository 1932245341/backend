package org.example.server.server.interfa;

import com.github.pagehelper.Page;
import org.example.common.result.PageResult;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Dish;

import java.util.List;

public interface DishService {
    void insert(Dish dish);
    void update(Dish dish);
    void deleteById(Integer id);
    List<Dish> queryByRestaurantId(long restaurant_id);


    PageResult queryPage(PageQueryDTO pageQueryDTO);

    Dish getById(Integer id);//菜品详细页
}
