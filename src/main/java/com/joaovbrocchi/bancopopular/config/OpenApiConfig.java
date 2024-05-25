package com.joaovbrocchi.bancopopular.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenaAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("api plataforma curso")
                        .version("v1")
                        .description("uma api de uma plataforma de venda de cursos")
                        .termsOfService("https://github.com/joaovbrocchi/javaApi")
                        .license(new License().name("Joaovbrocchi")
                                .url("https://github.com/joaovbrocchi")
                        )

                );
    }
}