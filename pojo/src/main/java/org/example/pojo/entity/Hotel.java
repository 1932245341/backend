package org.example.pojo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hotel implements Serializable {
    private int id;
    private long marketerId;
    private String name;
    private String location;
    private Float latitude; // 经度
    private Float longitude; // 纬度
    private String contact;
    private String label; //标签（三星；五星）
    private String openingYear;//开业年份
    private String fitmentYear;//装修年份
    private String license; // 营业执照图片url
    private String image; //封面图片
    private float score;
    private int status;//状态，0申请中，1已通过,2已拒绝
    private Integer floornum;
    private Integer roomnum;
    private BigDecimal minprice;
}
