package org.example.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Marketer implements Serializable {

    private long id;

    private String username;  //登录的用户名

    private String name; //商家名称

    private String password;

    private String phone;

    private String type;

    private String email;

    private int status;  //0提交中，1已接收，2已拒绝

    private String license;  //营业执照的图片url

    private String address;

    private String manager; //经办人

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
