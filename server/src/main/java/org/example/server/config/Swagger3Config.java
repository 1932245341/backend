package org.example.server.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Swagger3Config{

    // Swagger API 配置
    @Bean
    public OpenAPI springShopOpenAPI() {
        System.out.println("Swagger3Config.springShopOpenAPI()");
        return new OpenAPI()
                .info(new Info().title("i游阳朔")
                        .description("Swagger3 Spring Boot 3.4.3 application")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://www.itbeien.cn")))
                .externalDocs(new ExternalDocumentation()
                        .description("api接口文档")
                        .url("https://www.itbeien.cn"));
    }

}
