package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${spring.application.name:Demo Application}")
    private String applicationName;

    @Value("${app.version:1.0}")
    private String version;

    @Value("${app.description:Car Brand and Model Management System}")
    private String description;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .servers(serverList());
    }

    private Info apiInfo() {
        return new Info()
                .title(applicationName + " API")
                .version(version)
                .description(description)
                .license(new License().name("Onur Akalın").url("https://github.com/OnurAkalin"));
    }

    private List<Server> serverList() {
        return List.of(new Server().url("http://localhost:8080").description("Local Development Server"));
    }
}