package org.example.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Room implements Serializable {

    private int id;
    private Integer hotelId;
    private String name;
    private Integer capacity; //房间最多容纳人数
    private String floor; //房间所属房间
    private Integer area;
    private BigDecimal price;
    private String image;
    private String bed;



}
