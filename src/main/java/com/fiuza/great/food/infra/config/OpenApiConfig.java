package com.fiuza.great.food.infra.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI greatFood() {
        return new OpenAPI()
                .info(
                        new Info().title("Great Food")
                                .description("Projeto Desenvolvido como Tech Challenger da  Pós graduação em " +
                                        "Arquitetura e Desenvolvimento em JAVA")
                                .version("v0.0.1")
                                .license(new License().name("Apache 2.0")
                                        .url("https://github.com/MaiconFiuza/greatfoodpostech"))
                );
    }
}

