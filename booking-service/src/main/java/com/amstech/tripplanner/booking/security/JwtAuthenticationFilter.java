package com.amstech.tripplanner.booking.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
//	@Autowired
//	private TokenProvider tokenProvider; 
//	
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		log.info("JwtAuthenticationFilter: calling doFilterInternal merthod");
//		String requestURI=request.getRequestURI();
//		
//	}
//}


	@Autowired
	private TokenProvider tokenProvider;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		log.info("JwtAuthenticationFilter: calling doFilterInternal method");
		
		final String authToken =request.getHeader("Authorization");
		if(authToken==null) {
			log.info("Auth token does not exist,return....");
			
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			//response.getWriter().write("{'message':" );
			filterChain.doFilter(request, response);
		    response.setStatus(401);
		}
	
		log.info("authToken:[{}]" + authToken);
		
	}

}
