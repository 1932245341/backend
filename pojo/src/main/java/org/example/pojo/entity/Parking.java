package org.example.pojo.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class Parking implements Serializable {


    private int id;

    //停车场昵称
    private String name;

    //位置
    private String location;

    //图片url
    private String image;

    //纬度
    private float latitude;

    //经度
    private float longitude;

    //状态
    private int status;
}
