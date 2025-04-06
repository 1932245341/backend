package org.example.server.server.interfa;

import org.example.pojo.dto.AdminLoginDTO;
import org.example.pojo.entity.Admin;

public interface AdminService {
    /**
     * 员工登录
     *
     * @param adminLoginDTO
     * @return
     */
    Admin login(AdminLoginDTO adminLoginDTO);

}
