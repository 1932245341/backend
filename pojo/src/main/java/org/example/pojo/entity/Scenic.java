package org.example.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class Scenic implements Serializable {
    private int id;
    private String name;
    private String description;
    private String time;
    private String location;
    private Float latitude;
    private Float longitude;
    private String contact;
    private String image; // 景点封面图片
    private Float score;
    private BigDecimal minprice;
}
