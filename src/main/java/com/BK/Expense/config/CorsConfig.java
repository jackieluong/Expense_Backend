package com.BK.Expense.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("http://localhost:3000", "http://localhost:3001r") // Allow all origins
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS" ) // Allow all HTTP methods
                        .allowedHeaders("*")  // Allow all headers
                        .allowCredentials(true);
            }
        };
    }
}
