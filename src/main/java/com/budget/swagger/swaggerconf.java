package com.budget.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class swaggerconf  {

    @Bean
    public OpenAPI budgetApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Budgétaire")
                        .description("Description de votre API")
                        .version("1.0"));
    }
}
