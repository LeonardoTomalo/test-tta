package com.tta.test.backtesttta.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mongodb.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtils {
	
	private static final String SECRECT_KEY = "secret";
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		String userName = extractUsername(token);
		return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRECT_KEY).parseClaimsJws(token).getBody();
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private String createToken(Map<String, Object> claims, String subject) {
		Date now = new Date(System.currentTimeMillis());
		Date until = new Date(System.currentTimeMillis()+1000*60*60*10);		
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(now).setExpiration(until)
				.signWith(SignatureAlgorithm.HS256, SECRECT_KEY).compact();
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

}
