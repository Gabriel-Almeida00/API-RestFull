package com.gabriel.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAp() {
        return new OpenAPI()
                .info(new Info()
                        .title("RestFull API with java and Spring")
                        .version("v1")
                        .description("some description about API")
                        .termsOfService("https://github.com/Gabriel-Almeida00/API-Restfull")
                        .license(new License()
                                        .name("Apache 2.0")
                                        .url("https://github.com/Gabriel-Almeida00/API-Restfull")));
    }
}
