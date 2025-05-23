package org.example.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Parking implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;

    //停车场昵称
    private String name;

    //位置
    private String location;

    //图片url
    private String image;

    //纬度
    private Float latitude;

    //经度
    private Float longitude;

    //状态
    private Integer status;
}
