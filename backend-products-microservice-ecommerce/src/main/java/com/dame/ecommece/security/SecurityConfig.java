package com.dame.ecommece.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.cors().configurationSource(new CorsConfigurationSource() {
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration config = new CorsConfiguration();
						config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
						config.setAllowedMethods(Collections.singletonList("*"));
						config.setAllowCredentials(true);
						config.setAllowedHeaders(Collections.singletonList("*"));
						config.setExposedHeaders(Arrays.asList("Authorization"));
						config.setMaxAge(3600L);
						return config;
					}
				}).disable()
				.csrf(csrf -> csrf.disable())
				.authorizeRequests(authorizeRequests -> authorizeRequests
						// Consulter tous les produits
						.requestMatchers("/api/basket/1").hasAnyAuthority("ADMIN", "USER")
						.requestMatchers("/api/products/**").hasAnyAuthority("ADMIN", "USER")
						// Consulter un produit par son id
						.requestMatchers(HttpMethod.GET, "/api/**").hasAnyAuthority("ADMIN", "USER")
						// Ajouter un nouveau produit
						.requestMatchers(HttpMethod.POST, "/api/**").hasAuthority("ADMIN")
						// Modifier un produit
						.requestMatchers(HttpMethod.PUT, "/api/**").hasAuthority("ADMIN")
						// Supprimer un produit
						.requestMatchers(HttpMethod.DELETE, "/api/**").hasAuthority("ADMIN")
						.anyRequest().authenticated())
				.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
