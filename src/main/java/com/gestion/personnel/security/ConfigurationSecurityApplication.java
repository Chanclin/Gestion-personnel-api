package com.gestion.personnel.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class ConfigurationSecurityApplication {
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final JwtFilter jwtFilter;
	private final UserDetailsService userDetailsService;

	public ConfigurationSecurityApplication(final BCryptPasswordEncoder bCryptPasswordEncoder,
			final JwtFilter jwtFilter, final UserDetailsService userDetailsService) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.jwtFilter = jwtFilter;
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("http://localhost:8080", "https://gestion-personnel-464dbbb30049.herokuapp.com")); // Ajoutez l'URL de votre app Heroku
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
                    config.setAllowCredentials(true);
                    return config;
                }))
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/utilisateur/inscription").permitAll()
						.requestMatchers("/api/utilisateur/connexion").permitAll()
						.requestMatchers("/api/directions/**").authenticated()
						.requestMatchers("/api/entreprises/**").authenticated().anyRequest().authenticated())
				.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

				).addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(this.bCryptPasswordEncoder);
		return daoAuthenticationProvider;
	}
	
	

}
