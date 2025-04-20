package org.example.server.server.interfa;

import org.example.common.result.PageResult;
import org.example.pojo.dto.PageQueryDTO;

import org.example.pojo.entity.Scenic;

import java.util.List;

public interface ScenicService {

    void insert(Scenic scenic);
    void update(Scenic scenic);
    void deleteById(int id);
    List<Scenic> queryAllScenic();
    List<Scenic> recommendation();
    PageResult queryPage(PageQueryDTO pageQueryDTO);

}
