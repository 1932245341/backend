package org.example.server.server.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.common.context.BaseContext;
import org.example.common.result.PageResult;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Dish;
import org.example.pojo.entity.Ticket;
import org.example.server.mapper.DishMapper;
import org.example.server.mapper.TicketMapper;
import org.example.server.server.interfa.DishService;
import org.example.server.server.interfa.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public void insert(Ticket ticket) {
        ticketMapper.insert(ticket);
    }

    @Override
    public Ticket queryById(Integer id) { //详细页
        return ticketMapper.queryById(id);
    }

    @Override
    public void update(Ticket ticket) {
        ticketMapper.update(ticket);
    }

    @Override
    public void deleteById(Integer id) {
        ticketMapper.deleteById(id);
    }

    @Override
    public List<Ticket> queryByScenicId(long scenicId) {
        return ticketMapper.queryByScenicId(scenicId);
    }

    @Override
    public PageResult queryPage(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
        Page<Ticket> page = ticketMapper.pageQuery(pageQueryDTO);
        long total = page.getTotal();
        List<Ticket> records = page.getResult();
        return new PageResult(total,records);
    }


}
