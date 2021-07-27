package com.shopme.jwtUtill;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtill {

	private static final int TOKEN_VALIDITY = 5 * 3600;

	private static final String SECRETE_KEY = "pavankumarbasava";

	public String getUserNameFromUser(String token) {
		return getClaimFromToken(token, Claims::getSubject);

	}

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(SECRETE_KEY).parseClaimsJws(token).getBody();
	}

	public boolean validatetoken(String token, UserDetails userDetails) {
		String userName = getUserNameFromUser(token);
		return userName.equals(userDetails.getUsername()) && !isTokenExperied(token);
	}

	private boolean isTokenExperied(String token) {
		Date expirationDateFromToken = getExpirationDateFromToken(token);
		return expirationDateFromToken.before(new Date());
	}

	private Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();

		return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRETE_KEY).compact();

	}
}
