package org.example.server.config;


import lombok.extern.slf4j.Slf4j;
import org.example.server.interceptor.JwtTokenMarketerInterceptor;
import org.example.server.interceptor.JwtTokenUserInterceptor;
import org.example.server.interceptor.JwtTokenAdminInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.*;


@Configuration
@Slf4j
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;
    @Autowired
    private JwtTokenUserInterceptor jwtTokenUserInterceptor;
    @Autowired
    private JwtTokenMarketerInterceptor jwtTokenMarketerInterceptor;

    /**
     * 注册自定义拦截器
     *
     * @param registry
     */

    public void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");

        //管理员后台
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/admin/login");

        //商家后台
        registry.addInterceptor(jwtTokenMarketerInterceptor)
                .addPathPatterns("/marketer/**")
                .excludePathPatterns("/marketer/login");

        //用户端
        registry.addInterceptor(jwtTokenUserInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/user/login");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // use allowedOriginPatterns instead of allowedOrigins
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true);
    }
}


