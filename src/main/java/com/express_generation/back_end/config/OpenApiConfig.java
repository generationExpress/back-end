package com.express_generation.back_end.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI generationExpressAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Generation Express API")
                                .description("""
                                        API REST para la gestión de envíos,
                                        rastreo, usuarios y logística de
                                        Generation Express.
                                        """)
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .name("Generation Express")
                                                .email("support@generationexpress.com")
                                                .url("http://localhost:5501/pages/index.html")
                                )
                                .license(
                                        new License()
                                                .name("MIT")
                                )
                );
    }

}