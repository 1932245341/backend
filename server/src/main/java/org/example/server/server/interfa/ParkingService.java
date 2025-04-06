package org.example.server.server.interfa;

import org.example.common.result.PageResult;
import org.example.pojo.dto.ParkingPageQueryDTO;
import org.example.pojo.entity.Parking;

import java.util.List;

public interface ParkingService {

    void addParking(Parking parking);

    void updateParking(Parking parking);

    void deleteParking(int id);

    //Parking getname(String name);

    /**
     * 分页查询菜品
     *
     * @param parkingPageQueryDTO
     */
    PageResult queryPage(ParkingPageQueryDTO parkingPageQueryDTO);

    List<Parking> getAllParking();
}
