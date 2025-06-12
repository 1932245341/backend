package org.example.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class dataVO {
    private Integer userCount;   //用户数
    private BigDecimal specialtyTotal;  //特产总销售额
    private BigDecimal scenicTotal;   //景区总销售额
    private BigDecimal restaurantTotal;   //餐饮总销售额
    private BigDecimal hotelTotal;  //民宿总销售额

    private BigDecimal marketerrestaurantTotal;   //餐饮总销售额
    private BigDecimal marketerhotelTotal;  //民宿总销售额
}
