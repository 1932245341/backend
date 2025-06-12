package org.example.server.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Marketer;

import java.math.BigDecimal;
import java.util.List;


public interface MarketerMapper {

    void insert(Marketer marketer);

    void deleteById(int id);

    void update(Marketer marketer);

    /**
     * 根据用户名查询商家(用于登录)
     * @param username
     * @return
     */
    @Select("select * from marketer where username = #{username}")
    Marketer getByUsername(String username);

    /**
     * 分页查询所有商家
     * @param pageQueryDTO
     * @return
     */
    Page<Marketer> pageQuery(PageQueryDTO pageQueryDTO);

    @Select("select * from marketer where status = 0")
    List<Marketer> qureyApplication();

    @Select("select sum(total) from orders o join restaurant r on o.restaurant_id=r.id  where o.status='已支付' and r.marketer_id=#{marketer_id}")
    BigDecimal sumRestaurantTotal(long marketer_id);

    @Select("select sum(total) from orders o join hotel h on o.hotel_id=h.id  where o.status='已支付' and h.marketer_id= #{marketer_id}")
    BigDecimal sumHotelTotal(long marketer_id);
}
