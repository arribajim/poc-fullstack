package com.example.fileprocessing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer,WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    
    @Value("${apiUrl}")
    private String apiUrl;

    @Value("${server.port}")
    private String serverPort;

    @Override
    public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(apiUrl);
    }

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(Integer.parseInt(serverPort));
    }
}