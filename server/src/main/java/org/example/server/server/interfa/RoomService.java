package org.example.server.server.interfa;

import org.example.common.result.PageResult;
import org.example.pojo.dto.PageQueryDTO;

import org.example.pojo.entity.Room;

import java.util.List;

public interface RoomService {
    void insert(Room room);
    void update(Room room);
    void deleteById(Integer id);
    List<Room> queryByHotelId(long hotel_id);


    PageResult queryPage(PageQueryDTO pageQueryDTO);

    Room getById(Integer id);
}
