package org.example.server.controller.user;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.example.common.constant.JwtClaimsConstant;
import org.example.common.properties.JwtProperties;
import org.example.common.result.Result;
import org.example.common.utils.JwtUtils;
import org.example.pojo.dto.UserLoginDTO;
import org.example.pojo.entity.User;
import org.example.pojo.vo.UserLoginVO;
import org.example.server.server.interfa.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/user")
@Tag(name ="用户相关接口")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 微信登录
     * @param userLoginDTO 登录请求参数
     * @return 登录结果
     */
    @PostMapping("/login")
    @Operation(summary = "微信登录", description = "通过微信授权码实现用户登录") // 替换 @ApiOperation
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("微信用户登录：{}", userLoginDTO.getCode());

        // 微信登录业务处理
        User user = userService.wxLogin(userLoginDTO);

        // 生成JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtils.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims
        );
        log.info("微信用户JWT令牌: {}", token);

        // 构建响应对象
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(token)
                .build();
        log.info("微信用户登录响应：{}", userLoginVO);
        return Result.success(userLoginVO);
    }

    @Operation(summary = "退出登录", description = "用户注销当前登录状态") // 替换 @ApiOperation
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }
}