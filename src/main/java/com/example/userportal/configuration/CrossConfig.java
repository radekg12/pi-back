package com.example.userportal.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrossConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("http://localhost:4200", "http://192.168.0.55:4200", "http://192.168.0.55:8081", "http://192.168.0.54:8081", "http://localhost:8081")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
            .allowedHeaders("*")
            .maxAge(3600)
            .allowCredentials(true);
  }
}
