package org.example.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {

    private int id;
    private Long userId;
    private int specialtyId;
    private int addressId;
    private int number;
    private BigDecimal amount;
    private LocalDateTime createTime;

    private String name; //关联特产名
    private String image; //关联特产的封面图片

}
