package org.example.server.server.interfa;


import org.example.pojo.dto.UserLoginDTO;
import org.example.pojo.entity.User;

public interface UserService {

    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    User wxLogin(UserLoginDTO userLoginDTO);
}