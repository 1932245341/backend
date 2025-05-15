package org.example.server.server.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.common.context.BaseContext;
import org.example.common.result.PageResult;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Dish;

import org.example.server.mapper.DishMapper;
import org.example.server.server.interfa.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Override
    public void insert(Dish dish) {
        dishMapper.insert(dish);
    }

    @Override
    public void update(Dish dish) {
        dishMapper.update(dish);
    }

    @Override
    public void deleteById(Integer id) {
        dishMapper.deleteById(id);
    }

    @Override
    public List<Dish> queryByRestaurantId(long restaurant_id) {
        return dishMapper.queryByRestaurantId(restaurant_id);
    }

    @Override
    public PageResult queryPage(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
        Page<Dish> page = dishMapper.pageQuery(pageQueryDTO);
        long total = page.getTotal();
        List<Dish> records = page.getResult();
        return new PageResult(total,records);
    }

    @Override
    public Dish getById(Integer id) {
        return dishMapper.getById(id);
    }


}
