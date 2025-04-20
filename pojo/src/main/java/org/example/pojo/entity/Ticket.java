package org.example.pojo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ticket implements Serializable {
    private Integer id;
    private Integer scenicId;
    private String name;
    private Float price;
    private String label;
    private Integer sale;
}
