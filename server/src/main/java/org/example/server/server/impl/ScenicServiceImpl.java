package org.example.server.server.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.common.context.BaseContext;
import org.example.common.result.PageResult;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Restaurant;
import org.example.pojo.entity.Scenic;
import org.example.server.mapper.RestaurantMapper;
import org.example.server.mapper.ScenicMapper;
import org.example.server.server.interfa.RestaurantService;
import org.example.server.server.interfa.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenicServiceImpl implements ScenicService {

    @Autowired
    private ScenicMapper scenicMapper;

    @Override
    public void insert(Scenic scenic) {
        scenicMapper.insert(scenic);
    }

    @Override
    public void update(Scenic scenic) {
        scenicMapper.update(scenic);
    }

    @Override
    public void deleteById(int id) {
        scenicMapper.deleteById(id);
    }

    @Override
    public List<Scenic> queryAllScenic() {
        return scenicMapper.queryAllScenic();
    }

    @Override
    public List<Scenic> recommendation() {
        return scenicMapper.recommendation();
    }



    /**
     * 管理员端分页查询所有景区
     * @param pageQueryDTO
     * @return
     */
    @Override
    public PageResult queryPage(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
        Page<Scenic> page = scenicMapper.pageQuery(pageQueryDTO);
        long total = page.getTotal();
        List<Scenic> records = page.getResult();
        return new PageResult(total,records);
    }


}
