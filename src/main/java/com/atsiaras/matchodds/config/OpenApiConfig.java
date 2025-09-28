package com.atsiaras.matchodds.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI matchOddsOpenAPI() {
        return new OpenAPI()
                    .info(new Info()
                        .title("Match Odds API")
                        .version("1.0")
                        .description("REST web API for managing matches and their odds.")
                        .contact(new Contact()
                                .name("Thanasis Tsiaras")
                                .email("thanostsiar@gmail.com")
                        )
                );
    }
}
