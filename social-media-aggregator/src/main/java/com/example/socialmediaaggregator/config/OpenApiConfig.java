package com.example.socialmediaaggregator.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Social Media Aggregator API")
                        .version("1.0")
                        .description("API documentation for the Social Media Aggregator application.")
                        .contact(new Contact()
                                .name("Support Team")
                                .email("support@example.com")));
    }

    @Bean
    public OpenApiCustomizer customizer() {
        return openApi -> openApi.getPaths().forEach((name, path) -> path.readOperations().forEach(operation -> {
            operation.addTagsItem(name);
        }));
    }
}
