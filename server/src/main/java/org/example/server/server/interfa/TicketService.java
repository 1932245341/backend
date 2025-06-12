package org.example.server.server.interfa;

import org.example.common.result.PageResult;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Dish;
import org.example.pojo.entity.Ticket;
import org.example.pojo.vo.TicketBookVO;

import java.util.List;

public interface TicketService {
    void insert(Ticket ticket);
    void update(Ticket ticket);
    void deleteById(Integer id);
    List<Ticket> queryByScenicId(long scenicId);


    PageResult queryPage(PageQueryDTO pageQueryDTO);
    Ticket queryById(Integer id);

    void insertTicketBook(TicketBookVO ticketBookVO);
    List<TicketBookVO> selectTicketBooks();
}
