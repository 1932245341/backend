package org.example.server.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Room;

import java.util.List;

public interface RoomMapper {

    void deleteById(Integer id);

    void insert(Room room);

    void update(Room room);

    /*
     * 根据hotel_id查询套房,展示本民宿的所有套房
     */
    List<Room> queryByHotelId(long hotel_id);

    /**
     * 商家后台分页查询套房
     *
     * @param PageQueryDTO
     * @return
     */
    Page<Room> pageQuery(PageQueryDTO PageQueryDTO);

    //详细页
    @Select("select * from room where id = #{id}")
    Room getById(Integer id);
}
