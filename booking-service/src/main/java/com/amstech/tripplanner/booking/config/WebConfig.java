package com.amstech.tripplanner.booking.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        
        
    	registry.addResourceHandler("/trips/**")
            .addResourceLocations("file:./storage/trips/");
    
        // For user profile images
        registry.addResourceHandler("/users/**")
                .addResourceLocations("file:storage/users/");
        
        registry.addResourceHandler("/tripBanners/**")
        .addResourceLocations("file:storage/tripBanners/");
        
        registry.addResourceHandler("/aboutUs/**")
        .addResourceLocations("file:storage/aboutUs/");
        
        registry.addResourceHandler("/gallery/**")
        .addResourceLocations("file:storage/gallery/");
        
        registry.addResourceHandler("/user/**")
        .addResourceLocations("file:storage/user/");
       
        
    }
}
