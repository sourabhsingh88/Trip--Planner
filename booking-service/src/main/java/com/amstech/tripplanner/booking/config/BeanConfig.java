package com.amstech.tripplanner.booking.config;

import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BeanConfig {
	@Bean
	public ObjectMapper getObjectMapper() {
		return new  ObjectMapper();
	}
}
