package org.example.server.server.impl;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.example.common.result.PageResult;
import org.example.pojo.entity.Parking;
import org.example.server.mapper.ParkingMapper;
import org.example.server.server.interfa.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;
import org.example.pojo.dto.PageQueryDTO;

import java.util.List;

@Service
@Slf4j
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingMapper parkingMapper;

    @Override
    public void deleteParking(int id) {
        parkingMapper.deleteById(id);
    }

    @Override
    public void updateParking(Parking parking) {
        parkingMapper.update(parking);
    }

    @Override
    public List<Parking> getAllParking() {
        return parkingMapper.queryAll();
    }

    /**
     * 分页查询停车场
     *
     * @param pageQueryDTO
     */
    @Override
    public PageResult queryPage(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
        Page<Parking> page = parkingMapper.pageQuery(pageQueryDTO);
        long total = page.getTotal();
        List<Parking> records = page.getResult();
        return new PageResult(total,records);
    }

    @Override
    public void addParking(Parking parking) {
        parkingMapper.insert(parking);
    }
}
