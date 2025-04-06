package org.example.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data

public class AdminLoginDTO implements Serializable {

    private String username;

    private String password;

}
