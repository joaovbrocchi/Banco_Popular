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
                        .title("Chaves Pix Banco Popular")
                        .version("v1")
                        .description("api de controle das chaves pix do Banco Popular")
                        .termsOfService("https://github.com/joaovbrocchi/Banco_Popular")
                        .license(new License().name("Joaovbrocchi")
                                .url("https://github.com/joaovbrocchi")
                        )
                );
    }
}