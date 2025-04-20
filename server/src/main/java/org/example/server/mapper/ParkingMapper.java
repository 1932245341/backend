package org.example.server.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Parking;
import com.github.pagehelper.Page;

import java.util.List;

@Mapper
public interface ParkingMapper {

    //用户端查询所有停车场
    List<Parking> queryAll();

    /**
     * 分页查询停车场
     *
     * @param pageQueryDTO
     * @return
     */
    Page<Parking> pageQuery(PageQueryDTO pageQueryDTO);


    void insert(Parking parking);


    void deleteById(int id);

    void update(Parking parking);
}
