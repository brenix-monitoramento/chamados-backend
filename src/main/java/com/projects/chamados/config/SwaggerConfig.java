package com.projects.chamados.config;

import com.projects.chamados.utils.Constants;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Chamados API REST")
                                .version("1.0")
                                .description("Documentação dos endpoints da aplicação.")
                )
                .components(new Components()
                        .addSecuritySchemes(Constants.SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .name(Constants.SECURITY_SCHEME_NAME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Informe o token JWT.")));
    }
}
