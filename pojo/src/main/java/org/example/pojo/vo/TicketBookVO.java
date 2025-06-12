package org.example.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketBookVO {
    private Integer id;
    private Integer ticketId;
    private String scenicname;
    private Long userId;
    private String date;
    private Integer number;
    private BigDecimal totalPrice;
    private Integer status;
    private String idcard;
    private String name;  //游客姓名
    private String contact;
    private Integer used; //是否核销
}
