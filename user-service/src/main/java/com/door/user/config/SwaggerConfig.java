package com.door.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;




@Configuration
public class SwaggerConfig {
    
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Users API")
                        .version("1.0.0")
                        .description("Simple REST API crud to manage Users - Whit this api rest we can communicate own rest of services with users/roles tables to manage all tasks regardings with theirs")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));

    }

}
