package org.example.server.mapper;

import com.github.pagehelper.Page;
import org.example.pojo.dto.PageQueryDTO;

import org.example.pojo.entity.Scenic;

import java.util.List;

public interface ScenicMapper {

     void insert(Scenic scenic);
     void deleteById(int id);
     void update(Scenic scenic);


    /**
     * 用户端查询所有的景区(存入redis)
     * @return
     */
    List<Scenic> queryAllScenic();


    //用户端推荐的景区（评分前10名）
    List<Scenic> recommendation();

    /**
     * 管理员端分页查询所有景区
     * @param pageQueryDTO
     * @return
     */
    Page<Scenic> pageQuery(PageQueryDTO pageQueryDTO);
}
