package com.api.api_rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
        .info(new Info()
        .title("RESTfull API with Java 19 and Spring Boot 3")
        .version("v1")
        .description("People creation API")
        .termsOfService("Undefined")
        .license(new License().name("Apache License, Version 2.0")
        .url("localhost:8080/v1")));
    }
}
