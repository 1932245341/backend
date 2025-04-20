package org.example.server.controller.admin;
import org.example.common.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.example.common.properties.JwtProperties;
import org.example.common.result.Result;
import org.example.pojo.dto.AdminLoginDTO;
import org.example.pojo.entity.Admin;
import org.example.pojo.vo.AdminLoginVO;
import org.example.server.server.interfa.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.common.constant.JwtClaimsConstant;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/admin")
@Slf4j
public class AdminController {

    @Autowired
    private AdminService adminService;


    @Autowired
    private JwtProperties jwtProperties;


    /**
     * 登录
     * @param adminLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<AdminLoginVO> login(@RequestBody AdminLoginDTO adminLoginDTO) {
        log.info("管理员登录：{}", adminLoginDTO);

        Admin admin = adminService.login(adminLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.ADMIN_ID, admin.getId());

        //构造令牌
        String token = JwtUtils.createJWT(
                //签名所用的密匙，令牌类型 it
                //jwtProperties是配置属性类 配置了签名算法的类型，过期时间
                jwtProperties.getAdminSecretKey(),
                //过期时间
                jwtProperties.getAdminTtl(),
                //令牌中间的值
                claims);
        System.out.println("管理员控制器的token"+token);

        //生成对象响应给前端
        AdminLoginVO adminLoginVO = AdminLoginVO.builder().id(admin.getId()).userName(admin.getUsername()).name(admin.getName()).token(token).build();

        return Result.success(adminLoginVO);
    }

}
