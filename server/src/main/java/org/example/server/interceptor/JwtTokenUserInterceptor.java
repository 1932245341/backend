package org.example.server.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import lombok.extern.slf4j.Slf4j;
import org.example.common.constant.JwtClaimsConstant;
import org.example.common.context.BaseContext;
import org.example.common.properties.JwtProperties;
import org.example.common.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Enumeration;

/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenUserInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getUserTokenName());
        log.info("当前用户的令牌为：{}", token);

        //2、校验令牌
        try {
            log.info("jwt校验:{}", token);

            Jws<Claims> claimsJws = JwtUtils.parseJWT(token, jwtProperties.getUserSecretKey());
            Long userId = Long.valueOf(claimsJws.getPayload().get(JwtClaimsConstant.USER_ID).toString());
            log.info("当前用户的id:{}", userId);
            BaseContext.setCurrentId(userId);
            //3、通过，放行
            return true;
        } catch (Exception ex) {
            //4、不通过，响应401状态码
            response.setStatus(401);
            return false;
        }
    }
}