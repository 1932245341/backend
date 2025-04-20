package org.example.server.server.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.common.context.BaseContext;
import org.example.common.result.PageResult;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Hotel;
import org.example.server.mapper.HotelMapper;
import org.example.server.server.interfa.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public void insert(Hotel hotel) {
        hotel.setMarketerId(BaseContext.getCurrentId());
        hotelMapper.insert(hotel);
    }

    @Override
    public void update(Hotel hotel) {
        hotelMapper.update(hotel);
    }

    @Override
    public void deleteById(int id) {
        hotelMapper.deleteById(id);
    }

    @Override
    public List<Hotel> queryAllHotel() {
        return hotelMapper.queryAllHotel();
    }

    @Override
    public List<Hotel> queryByprice() {
        return hotelMapper.queryByprice();
    }

    @Override
    public List<Hotel> queryByMarkerId(int markerId) {
        return hotelMapper.queryByMarkerId(markerId);
    }


    /**
     * 管理员端分页查询所有民宿
     * @param pageQueryDTO
     * @return
     */
    @Override
    public PageResult queryPage(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
        Page<Hotel> page = hotelMapper.pageQuery(pageQueryDTO);
        long total = page.getTotal();
        List<Hotel> records = page.getResult();
        return new PageResult(total,records);
    }


}
