package org.example.server.server.interfa;

import org.example.common.result.PageResult;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Parking;

import java.util.List;

public interface ParkingService {

    void addParking(Parking parking);

    void updateParking(Parking parking);

    void deleteParking(int id);

    //Parking getname(String name);

    /**
     * 分页查询停车场
     *
     * @param pageQueryDTO
     */
    PageResult queryPage(PageQueryDTO pageQueryDTO);

    List<Parking> getAllParking();
}
