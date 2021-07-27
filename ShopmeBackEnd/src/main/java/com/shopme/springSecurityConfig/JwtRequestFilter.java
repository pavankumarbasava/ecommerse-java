package com.shopme.springSecurityConfig;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.SecurityContext;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shopme.jwtUtill.JwtUtill;

import io.jsonwebtoken.ExpiredJwtException;
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtill jwtUtill;

	@Autowired
	private JwtUserDetaiService jwtUserDetaiService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String header = request.getHeader("Authorization");
		String jwtToken = null;
		String userNameFromtoken = null;
		if (header != null && header.startsWith("Bearer ")) {
			jwtToken = header.substring(7);

			try {
				userNameFromtoken = jwtUtill.getUserNameFromUser(jwtToken);

			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT token");
			} catch (ExpiredJwtException e) {
				System.out.println("Jwt token was expired");
			}

		}else {
			System.out.println("Jwt token doest start with Bearer");
		}
		
		if(userNameFromtoken!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = jwtUserDetaiService.loadUserByUsername(userNameFromtoken);
		
		if(jwtUtill.validatetoken(jwtToken, userDetails)) {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
					new UsernamePasswordAuthenticationToken(userDetails,  null, userDetails.getAuthorities() );
			
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
		
		}
		
		filterChain.doFilter(request, response);
	}

}
