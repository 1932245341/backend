package org.example.server.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Marketer;


public interface MarketerMapper {

    void insert(Marketer marketer);

    void deleteById(int id);

    void update(Marketer marketer);

    /**
     * 根据用户名查询商家(用于登录)
     * @param username
     * @return
     */
    @Select("select * from marketer where username = #{username}")
    Marketer getByUsername(String username);

    /**
     * 分页查询所有商家
     * @param pageQueryDTO
     * @return
     */
    Page<Marketer> pageQuery(PageQueryDTO pageQueryDTO);
}
