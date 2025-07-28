package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                                "http://localhost:3000",
                                "https://school-management-hy1ad6o93-writesunil21s-projects.vercel.app",
                                "https://school-management-app-ivory-eight.vercel.app",
                                "https://school-management.vercel.app"
                        )
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
}

