package org.example.pojo.entity;

import lombok.Data;

@Data
public class Comment {
    private Integer id;
    private Long userId;
    private Integer scenicId;  //如果是景区
    private Integer hotelId;   //如果是酒店
    private Integer restaurantId;
    private Integer specialtyId;
    private Integer score;
    private String content;
    private String createTime;
    private String image;
}
