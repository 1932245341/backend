package org.example.server.server.interfa;


import org.example.common.result.PageResult;
import org.example.pojo.dto.MarketerLoginDTO;
import org.example.pojo.dto.PageQueryDTO;

import org.example.pojo.entity.Marketer;

public interface MarketerService {

    /**
     * 商家登录
     *
     * @param marketerLoginDTO
     * @return
     */
    Marketer login(MarketerLoginDTO marketerLoginDTO);


    void addMarketer(Marketer marketer);
    void deleteMarketer(int id);
    void updateMarketer(Marketer marketer);
    PageResult queryPage(PageQueryDTO pageQueryDTO);
}
