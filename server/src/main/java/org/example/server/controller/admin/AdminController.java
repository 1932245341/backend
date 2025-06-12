package org.example.server.controller.admin;
import org.example.common.context.BaseContext;
import org.example.common.result.PageResult;
import org.example.common.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.example.common.properties.JwtProperties;
import org.example.common.result.Result;
import org.example.pojo.dto.AdminLoginDTO;
import org.example.pojo.dto.PageQueryDTO;
import org.example.pojo.entity.Admin;
import org.example.pojo.entity.Order;
import org.example.pojo.vo.AdminLoginVO;
import org.example.pojo.vo.dataVO;
import org.example.server.server.interfa.AdminService;
import org.example.server.server.interfa.HotelService;
import org.example.server.server.interfa.OrderService;
import org.example.server.server.interfa.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.common.constant.JwtClaimsConstant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/admin")
@Slf4j
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private OrderService orderService;

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


    @GetMapping("/data")
    public Result<dataVO> getData() {
        dataVO dataVO = adminService.getData();
        return Result.success(dataVO);
    }

    @GetMapping("/order/list")
    public Result<List<Order>> getByUserId() {
        List<Order> list = orderService.getAllOrder();
        return Result.success(list);
    }

    @GetMapping("/order/payed")
    public Result<List<Order>> getByUserIdPayed() {
        Order order = new Order();
        order.setStatus("已支付");
        List<Order> list = orderService.getByUserId(order);
        return Result.success(list);
    }
}
