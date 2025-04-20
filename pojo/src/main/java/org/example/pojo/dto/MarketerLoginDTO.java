
package org.example.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MarketerLoginDTO implements Serializable {

    private String username;

    private String password;

}