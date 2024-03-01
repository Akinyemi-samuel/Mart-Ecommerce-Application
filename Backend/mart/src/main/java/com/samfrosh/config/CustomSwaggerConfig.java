package com.samfrosh.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


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
@EnableWebMvc
@Configuration
//@Import(SwaggerConfig.class)
 class CustomSwaggerConfig {


    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
    };

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(AUTH_WHITELIST);
    }

    public CustomSwaggerConfig() {
        SpringDocUtils.getConfig().addAnnotationsToIgnore(AuthenticationPrincipal.class);
    }

}
