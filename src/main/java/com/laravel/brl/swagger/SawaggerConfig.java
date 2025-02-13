package com.laravel.brl.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SawaggerConfig {
	
	@Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("BRL API")
                        .version("1.0.0")
                        .description("This is a sample OpenAPI documentation for my application."));
    }

}
