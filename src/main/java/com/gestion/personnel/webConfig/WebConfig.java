package com.gestion.personnel.webConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Remplacez par le chemin de votre API
                .allowedOrigins("http://localhost:8080", "https://gestion-personnel-464dbbb30049.herokuapp.com") // Ajoutez l'URL du frontend Heroku
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Méthodes autorisées
                .allowedHeaders("Authorization", "Content-Type", "X-Requested-With")
                .allowCredentials(true); // Si vous utilisez des cookies ou des sessions
    }
}
