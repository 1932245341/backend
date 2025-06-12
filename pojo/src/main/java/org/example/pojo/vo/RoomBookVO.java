package org.example.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomBookVO {
    private Integer id;
    private Integer roomId;
    private String hotelname;
    private Long userId;
    private String checkIn;
    private String departure;
    private BigDecimal totalPrice;
    private String guest;
    private String idcard;
    private String contact;
    private String remark; //备注
    private String status;  //支付状态
    private Integer marketerId;
}
