package org.example.server.server.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.common.context.BaseContext;
import org.example.common.result.PageResult;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Dish;
import org.example.pojo.entity.Room;
import org.example.server.mapper.DishMapper;
import org.example.server.mapper.RoomMapper;
import org.example.server.server.interfa.DishService;
import org.example.server.server.interfa.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public void insert(Room room) {
        roomMapper.insert(room);
    }

    @Override
    public void update(Room room) {
        roomMapper.update(room);
    }

    @Override
    public void deleteById(Integer id) {
        roomMapper.deleteById(id);
    }

    @Override
    public List<Room> queryByHotelId(long hotel_id) {
        return roomMapper.queryByHotelId(hotel_id);
    }

    @Override
    public PageResult queryPage(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
        Page<Room> page = roomMapper.pageQuery(pageQueryDTO);
        long total = page.getTotal();
        List<Room> records = page.getResult();
        return new PageResult(total,records);
    }


}
