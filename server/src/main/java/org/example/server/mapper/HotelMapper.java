package org.example.server.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Dish;
import org.example.pojo.entity.Hotel;
import org.example.pojo.entity.Restaurant;

import java.util.List;

public interface HotelMapper {

     void insert(Hotel hotel);
     void deleteById(int id);
     void update(Hotel hotel);

    List<Hotel> userquery(Hotel hotel);

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


    /// 根据id查询详细页
    Hotel queryById(int id);

    /**
     * 查询本商家的民宿
     * @param markerId
     * @return
     */
    List<Hotel> queryByMarkerId(long markerId);


    /**
     * 管理员端分页查询所有商家的民宿
     * @param pageQueryDTO
     * @return
     */
    Page<Hotel> pageQuery(PageQueryDTO pageQueryDTO);


}
