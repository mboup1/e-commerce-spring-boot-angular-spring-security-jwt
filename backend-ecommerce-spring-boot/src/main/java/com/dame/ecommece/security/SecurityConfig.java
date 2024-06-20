//package com.dame.ecommece.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@EnableWebSecurity
//@Configuration
//public class SecurityConfig {
//
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.csrf(csrf -> csrf.disable())
//				.authorizeRequests(authorizeRequests -> authorizeRequests
//						// Consulter tous les produits
//						.requestMatchers("/api/products/**").hasAnyAuthority("ADMIN", "USER")
//						// Consulter un produit par son id
//						.requestMatchers(HttpMethod.GET, "/api/**").hasAnyAuthority("ADMIN", "USER")
//						// Ajouter un nouveau produit
//						.requestMatchers(HttpMethod.POST, "/api/**").hasAuthority("ADMIN")
//						// Modifier un produit
//						.requestMatchers(HttpMethod.PUT, "/api/**").hasAuthority("ADMIN")
//						// Supprimer un produit
//						.requestMatchers(HttpMethod.DELETE, "/api/**").hasAuthority("ADMIN")
//						.anyRequest().authenticated())
//				.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//		return http.build();
//	}
//}
