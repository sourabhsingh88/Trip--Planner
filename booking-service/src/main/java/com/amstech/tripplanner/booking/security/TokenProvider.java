package com.amstech.tripplanner.booking.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class TokenProvider {

	private String jwtSecret = "4261656C64785E674261656C64756E96";
	

	private SecretKey getSigningKey() {

		byte[] keyBytes = this.jwtSecret.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);

	}

	public void generateToken(String firstName, String lastName, String email, Integer userId, List<String> roleList,
			HttpServletResponse httpServletResponse) {
		try {
			String token = Jwts.builder().claim("firstName", firstName).claim("lastName", lastName)
					.claim("email", email).claim("userId", userId).claim("roleList", roleList).setSubject(email)
					.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 144000000)) 
					.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
			httpServletResponse.addHeader("token", token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getAuthentication(String token, HttpServletResponse httpServletResponse) {
		if (!validateToken(token)) {
			httpServletResponse.setStatus(401);
			return null;
		}
		Claims claims = Jwts.parser().parseClaimsJws(token).getBody();
		String email = (String) claims.get("email");
		String firstName = (String) claims.get("firstName");
		String lastName = (String) claims.get("lastName");
		Integer userId = (Integer) claims.get("userId");
		List<String> roleList = (List) claims.get("roleList");

		System.out.println(roleList);
		System.out.println(email);
		return null;

	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJwt(token);
			return true;
//		} catch (SignatureException ex) {
//			// Invalide Signature/claims
		} catch (ExpiredJwtException ex) {
			// Expired token
		} catch (UnsupportedJwtException ex) {
			// UnsupportedJwt token
		} catch (MalformedJwtException ex) {
			// MalformedJwt token
		} catch (IllegalArgumentException ex) {
			// jwt token is empty
		}
		return false;
	}
	
	public static void main(String[] args) {
		boolean status =new TokenProvider().validateToken("4261656C64785E674261656C64756E96");
		System.out.println(status);
	}
}


//(1000 *
// 60 *
// 60 *
// 24 *
// 7) 7
// days
