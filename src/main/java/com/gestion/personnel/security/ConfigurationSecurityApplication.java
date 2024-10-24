package com.gestion.personnel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gestion.personnel.services.UtilisateurService;
import com.gestion.personnel.services.UtilisateurServiceImpl;

@Configuration	
@EnableWebSecurity
public class ConfigurationSecurityApplication {
	
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Désactiver CSRF
            .authorizeHttpRequests(authorize -> authorize
            		.requestMatchers("/api/inscription").permitAll()
                	.requestMatchers("/api/connexion").permitAll()
                .anyRequest().authenticated() // Nécessite une authentification pour toutes les autres requêtes
            );
        
        return http.build(); // Construire la chaîne de filtres
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    	return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    //Montre où on va recup les info de l'utilisateur
    public UserDetailsService userDetailsService() {
    	return new UtilisateurServiceImpl();
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider (UtilisateurService utilisateurService) {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
        return daoAuthenticationProvider;
    }

}
