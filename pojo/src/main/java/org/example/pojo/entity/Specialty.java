package org.example.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Specialty implements Serializable {
    private int id;
    private String name;
    private BigDecimal price;
    private String type;
    private String description;
    private String image;
    private int sale;

}
