package org.example.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单表
*/
@Data
public class Order  {

    /** ID */
    private Integer id;
    /** 订单编号 */
    private String orderNo;
    /** 用户ID */
    private Long userId;
    /** 商品名称 */
    private String name;
    /** 商品数量 */
    private Integer num;
    /** 总价格 */
    private BigDecimal total;
    /** 创建时间 */
    private String createTime;
    /** 支付编号 */
    private String payNo;
    /** 支付时间 */
    private String payTime;
    /** 订单状态 */
    private String status;

    private String type;

    private Integer addressId;

    private Integer marketerId;

    private Integer specialtyId;
    private Integer hotelId;
    private Integer restaurantId;
    private Integer scenicId;
    private Integer ticketId;
    private Integer roomId;
    private Integer dishId;

    private String image; // 订单封面图片

    private String date;
    private String checkIn;
    private String departure;
    private String storename;
}