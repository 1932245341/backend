package org.example.server.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Dish;
import org.example.pojo.entity.Scenic;
import org.example.pojo.entity.Ticket;
import org.example.pojo.vo.TicketBookVO;

import java.util.List;

public interface TicketMapper {

    void deleteById(Integer id);

    void insert(Ticket ticket);

    void update(Ticket ticket);

    /*
     * 根据scenic_id查询门票,展示本景区的所有门票
     */
    List<Ticket> queryByScenicId(long scenic_id);

    /**
     * 管理员后台分页查询门票
     *
     * @param pageQueryDTO
     * @return
     */
    Page<Ticket> pageQuery(PageQueryDTO pageQueryDTO);

    Ticket queryById(Integer id);




    // 插入 TicketBook
    @Insert("INSERT INTO ticket_book (ticket_id, user_id,scenicname, date, number, total_price,  idcard, name, contact) " +
            "VALUES (#{ticketId}, #{userId}, #{scenicname},#{date}, #{number}, #{totalPrice},  #{idcard}, #{name}, #{contact})")
    void insertTicketBook(TicketBookVO ticketBookVO);


    @Select("SELECT * FROM ticket_book ")
    List<TicketBookVO> selectTicketBook();


}
