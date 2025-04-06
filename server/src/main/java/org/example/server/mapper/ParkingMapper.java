package org.example.server.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.dto.ParkingPageQueryDTO;
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
     * @param parkingPageQueryDTO
     * @return
     */
    Page<Parking> pageQuery(ParkingPageQueryDTO parkingPageQueryDTO);


    void insert(Parking parking);


    void deleteById(int id);

    void update(Parking parking);
}
