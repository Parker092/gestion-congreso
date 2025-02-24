package com.example.gestion_congreso.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestión de Congreso API")
                        .version("1.0")
                        .description("API para la gestión de congresos científicos, incluyendo autenticación, sesiones, trabajos y más."));
    }
}
