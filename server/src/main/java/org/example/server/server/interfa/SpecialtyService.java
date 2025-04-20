package org.example.server.server.interfa;

import org.example.common.result.PageResult;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Specialty;

import java.util.List;

public interface SpecialtyService {

    public void addSpecialty(Specialty specialty);

    public void updateSpecialty(Specialty specialty);

    public void deleteSpecialty(int id);

    public List<Specialty> getAllSpecialty();

    public List<Specialty> BySale();

    public List<Specialty> ByPriceDesc();

    public List<Specialty> ByPriceAsc();

    public List<Specialty> ByType(String type);

    public List<Specialty> search(String name);

    /**
     * 管理员端分页查询特产
     *
     * @param pageQueryDTO
     */
    PageResult queryPage(PageQueryDTO pageQueryDTO);

}
