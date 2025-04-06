package org.example.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ParkingPageQueryDTO implements Serializable {

    private int page;

    private int pageSize;

    private String name;


}
