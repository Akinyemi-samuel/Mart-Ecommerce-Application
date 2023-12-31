package com.samfrosh.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Akinyemi samuel",
                        email = "Akinyemisamuelayo@gmail.com",
                        url = "http://"
                ),
                description = "Mart Ecommerce Application for shopping",
                title = "Mart Ecommerce Application",
                version = "1.0",
                license = @License(
                        name = "Samuel licenses",
                        url = "http://"
                ),
                termsOfService = "Terms of Service"
        ),
        servers = {
                @Server(
                        description = "Local server for application development",
                        url = "http://localhost:8080/api/v2"
                ),
                @Server(
                        description = "Production Server of application deployment",
                        url = "https://production-server-name/api/v2"
                )
        }
)
public class SwaggerConfig {
}
