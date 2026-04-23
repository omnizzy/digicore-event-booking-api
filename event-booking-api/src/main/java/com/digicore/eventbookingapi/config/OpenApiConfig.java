package com.digicore.eventbookingapi.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI eventBookingApiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Event Booking API")
                        .description("REST API for managing events and seat bookings")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Nathan Adebesin")
                                .email("nathanadebesin@gmail.com")
                                .url("https://github.com/omnizzy"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Repository")
                        .url("https://github.com/omnizzy/digicore-event-booking-api"));
    }
}