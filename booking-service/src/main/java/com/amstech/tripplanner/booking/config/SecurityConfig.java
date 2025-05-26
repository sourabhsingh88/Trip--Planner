package com.amstech.tripplanner.booking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.amstech.tripplanner.booking.security.JwtAuthenticationFilter;

import lombok.extern.java.Log;

@Configuration
@EnableWebSecurity
@Log
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//			
//	
//		http.csrf().disable().authorizeHttpRequests().requestMatchers("/user/login").permitAll().anyRequest()
//				.authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
//
//	//			.addFilterBefore(JwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//		return http.build();
//	
//}

	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//		http
//		.csrf(csrf -> csrf.disable()) 
//		.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());  
//
//	return http.build();
//	}
//
//		http.csrf().disable().authorizeHttpRequests().requestMatchers("/user/login").permitAll().anyRequest()
//				.authenticated().and().exceptionHandling().and().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//		return http.build();
//
//	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
		.csrf(csrf -> csrf.disable()) 
		.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); 

	return http.build();
	}
	
}