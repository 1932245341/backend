package org.example.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.entity.Admin;

@Mapper
public interface AdminMapper {

    /**
     * 根据用户名查询员工(用于登录)
     * @param username
     * @return
     */
    @Select("select * from admin where username = #{username}")
    Admin getByUsername(String username);

}
