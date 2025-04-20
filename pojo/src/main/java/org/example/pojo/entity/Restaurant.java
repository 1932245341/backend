package org.example.pojo.entity;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class Restaurant implements Serializable {
    private int id;
    private Integer marketerId;
    private String name;
    private String location;
    private Float latitude; // 经度
    private Float longitude; // 纬度
    private String contact;

    private String license; // 营业执照图片url
    private String image;  //封面图片
    private Float score;
    private Integer status;//状态，0申请中，1已通过,2已拒绝
    private BigDecimal minprice;

}
