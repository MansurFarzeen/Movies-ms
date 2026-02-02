package org.com.ex.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI movieMsOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Movie Management Service API")
                        .description("API for managing movies, search, and info")
                        .version("1.0"));
    }
}