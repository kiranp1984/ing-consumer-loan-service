package com.ing.loan.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .servers(Collections.singletonList(new Server().url("")));
    }
}
