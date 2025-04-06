package org.example.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AdminLoginVO implements Serializable { //管理员登录返回的数据格式

    private Long id;

    private String userName;


    private String name;

    private String token;

}
