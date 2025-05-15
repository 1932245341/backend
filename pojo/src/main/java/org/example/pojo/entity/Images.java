package org.example.pojo.entity;

import lombok.Data;

@Data
public class Images {
    private Integer id;
    private Integer RestaurantId;
    private Integer HotelId;
    private Integer ScenicId;
    private String image;
}
