package org.example.server.server.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.common.result.PageResult;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Specialty;
import org.example.server.mapper.SpecialtyMapper;
import org.example.server.server.interfa.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    @Autowired
    private SpecialtyMapper specialtyMapper;

    /**
     * 添加特产
     * @param specialty
     */
    @Override
    public void addSpecialty(Specialty specialty) {
        specialtyMapper.insert(specialty);
    }

    /**
     * 修改特产
     * @param specialty
     * @return
     */
    @Override
    public void updateSpecialty(Specialty specialty) {
        specialtyMapper.update(specialty);
    }


    /**
     * 删除特产
     * @param id
     */
    @Override
    public void deleteSpecialty(int id) {
        specialtyMapper.deleteById(id);
    }

    @Override
    public List<Specialty> ByType(String type) {
        return specialtyMapper.ByType(type);
    }

    @Override
    public Specialty getSpecialtyById(int id) {
        return specialtyMapper.getSpecialtyById(id);
    }

    /**
     * 查询所有特产（综合排序）
     * @return
     */
    @Override
    public List<Specialty> getAllSpecialty() {
        return specialtyMapper.list();
    }

    @Override
    public List<Specialty> search(String name) {
        return specialtyMapper.search(name);
    }


    /**
     * 根据销量排序
     * @return
     */
    @Override
    public List<Specialty> BySale(){
        return specialtyMapper.BySale();
    }

    /**
     * 根据价格降序
     * @return
     */
    @Override
    public List<Specialty> ByPriceDesc(){
        return specialtyMapper.ByPriceDesc();
    }

    /**
     * 根据价格升序
     * @return
     */
    @Override
    public List<Specialty> ByPriceAsc(){
        return specialtyMapper.ByPriceAsc();
    }

    /**
     * 管理员端分页查询特产
     * @param pageQueryDTO
     * @return
     */
    @Override
    public PageResult queryPage(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
        Page<Specialty> page = specialtyMapper.pageQuery(pageQueryDTO);
        long total = page.getTotal();
        List<Specialty> records = page.getResult();
        return new PageResult(total,records);
    }
}
