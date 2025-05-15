package org.example.server.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Dish;

import java.util.List;

public interface DishMapper {

    void deleteById(Integer id);

    void insert(Dish dish);

    void update(Dish dish);

    /*
     * 根据restaurant_id查询菜品,展示本餐馆的所有菜品
     */
    List<Dish> queryByRestaurantId(long restaurant_id);

    /**
     * 商家后台分页查询菜品
     *
     * @param PageQueryDTO
     * @return
     */
    Page<Dish> pageQuery(PageQueryDTO PageQueryDTO);

    @Select("select * from dish where id = #{id}")
    Dish getById(Integer id);

}
