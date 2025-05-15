package org.example.server.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.dto.PageQueryDTO;

import org.example.pojo.entity.Specialty;

import java.util.List;

@Mapper
public interface SpecialtyMapper {

    void insert(Specialty specialty);

    Specialty getById(int id);

    void update(Specialty specialty);

    void deleteById(int id);

    List<Specialty> list();

    Specialty getSpecialtyById(int id);//详细页

    @Select("select * from specialty WHERE name LIKE CONCAT('%', #{name}, '%') ")
    List<Specialty> search(String name);

    @Select("select * from specialty where type = #{type}")
    List<Specialty> ByType(String type);

    @Select("select * from specialty order by sale desc")
    List<Specialty> BySale();

    //价格降序
    @Select("select * from specialty order by price desc")
    List<Specialty> ByPriceDesc();

    //价格升序
    @Select("select * from specialty order by price asc")
    List<Specialty> ByPriceAsc();

    /**
     * 管理员端分页查询特产
     *
     * @param pageQueryDTO
     * @return
     */
    Page<Specialty> pageQuery(PageQueryDTO pageQueryDTO);
}
