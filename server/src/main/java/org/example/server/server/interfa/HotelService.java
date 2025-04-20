package org.example.server.server.interfa;

import org.example.common.result.PageResult;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Hotel;

import java.util.List;

public interface HotelService {

    void insert(Hotel hotel);
    void update(Hotel hotel);
    void deleteById(int id);
    List<Hotel> queryAllHotel();
    List<Hotel> queryByprice();
    PageResult queryPage(PageQueryDTO pageQueryDTO);
    List<Hotel> queryByMarkerId(int markerId);
}
