package com.darknights.devigation.global.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi getPostApi(){

        return GroupedOpenApi
                .builder()
                .group("Devigation Swagger v1 post")
                .pathsToMatch("/v1/post/**")
                .build();
    }

    @Bean
    public OpenAPI getOpenApi(){

        return new OpenAPI().components(new Components())
                .info(getInfo());
    }

    private Info getInfo(){
        return new Info()
                .version("1.0.0")
                .description("➡️DEVIGATION REST API DOCS⬅️")
                .title("DEVIGATION");
    }
}