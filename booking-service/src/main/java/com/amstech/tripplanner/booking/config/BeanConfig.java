package com.amstech.tripplanner.booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
@Configuration
public class BeanConfig {
	@Bean
	public ObjectMapper getObjectMapper() {
		return new  ObjectMapper();
	}
	 @Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	 }
}
