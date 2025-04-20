package org.example.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageQueryDTO implements Serializable {

    private int page;

    private Integer scenicId;

    private Integer hotelId;

    private Integer restaurantId;

    private int pageSize;

    private String name;


}
