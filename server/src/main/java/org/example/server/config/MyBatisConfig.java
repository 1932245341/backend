package org.example.server.config;

import com.github.pagehelper.PageInterceptor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@MapperScan("org.example.server.mapper")
public class MyBatisConfig {

    @Bean
    public PageInterceptor pageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql"); // 设置数据库方言
        properties.setProperty("reasonable", "true");  // 开启合理化分页
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }
}
