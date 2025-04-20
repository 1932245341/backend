package org.example.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dish implements Serializable {
    private int id;
    private Long restaurantId;
    private String name;
    private String image;
    private BigDecimal price;
    private String description;
    private Integer status;
    private int sale;
}
