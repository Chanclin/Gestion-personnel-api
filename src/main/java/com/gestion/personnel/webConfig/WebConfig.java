package com.gestion.personnel.webConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Remplacez par le chemin de votre API
                .allowedOrigins("http://localhost:4200") // L'origine de votre application Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Méthodes autorisées
                .allowedHeaders("Authorization", "Content-Type", "X-Requested-With")
                .allowCredentials(true); // Si vous utilisez des cookies ou des sessions
    }
}
