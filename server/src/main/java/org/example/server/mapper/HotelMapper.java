package org.example.server.mapper;

import com.github.pagehelper.Page;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Hotel;

import java.util.List;

public interface HotelMapper {

     void insert(Hotel hotel);
     void deleteById(int id);
     void update(Hotel hotel);


    /**
     * 用户端查询所有的民宿（平台严选）(存入redis)
     * @return
     */
    List<Hotel> queryAllHotel();

    /**
     * 用户端查询所有的民宿（价格优先）(存入redis)
     * @return
     */
    List<Hotel> queryByprice();




    /**
     * 查询本商家的民宿
     * @param markerId
     * @return
     */
    List<Hotel> queryByMarkerId(int markerId);


    /**
     * 管理员端分页查询所有商家的民宿
     * @param pageQueryDTO
     * @return
     */
    Page<Hotel> pageQuery(PageQueryDTO pageQueryDTO);
}
