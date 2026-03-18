package com.placementportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.*;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // ✅ Allow frontend domains (local + deployed)
        config.setAllowedOrigins(List.of(
                "http://localhost:3000",
                "http://localhost:5173",
                "https://placement-portal-frontend-dcnn.onrender.com"
        ));

        // ✅ Allow all common methods
        config.setAllowedMethods(List.of(
                "GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"
        ));

        // ✅ Allow all headers (simpler + safer for now)
        config.setAllowedHeaders(List.of("*"));

        // ✅ Allow credentials (JWT, cookies if needed)
        config.setAllowCredentials(true);

        // ✅ Expose Authorization header
        config.setExposedHeaders(List.of("Authorization"));

        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
