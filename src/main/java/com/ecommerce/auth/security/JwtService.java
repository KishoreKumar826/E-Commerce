package com.ecommerce.auth.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private static final String SECRET_KEY = "mysecretkeymysecretkeymysecretkey12";

	// Generate a JWT token for the given email
	public String generateToken(String email) {

		return Jwts.builder().setSubject(email).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	// Validate the JWT token and return the email if valid
	private Key getSignInKey() {

		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}

	// Extract the email (username) from the JWT token
	public String extractUsername(String token) {

		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody().getSubject();
	}

	   // Validate the JWT token against the provided email
	public boolean isTokenValid(String token, String email) {

		final String username = extractUsername(token);

		return username.equals(email);
	}

}
