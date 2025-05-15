package org.example.server.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.entity.Images;

import java.util.List;

public interface ImagesMapper {

    @Insert("insert into images(restaurant_id,hotel_id,scenic_id,image) values(#{RestaurantId},#{HotelId},#{ScenicId},#{image})")
    void insert(Images images);

    @Delete("delete from images where id = #{id}")
    void deleteById(int id);

    @Select("select * from images where restaurant_id = #{RestaurantId}")
    List<Images> ByRestaurantId(int RestaurantId);

    @Select("select * from images where hotel_id = #{HotelId}")
    List<Images> ByHotelId(int HotelId);

    @Select("select * from images where scenic_id = #{ScenicId}")
    List<Images> ByScenicId(int ScenicId);
}
