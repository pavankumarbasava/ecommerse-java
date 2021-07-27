package com.shopme.springSecurityConfig;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shopme.dao.UserRepository;
import com.shopme.entity.User;
import com.shopme.jwtUtill.JwtUtill;
import com.shopme.jwtUtill.jwtEntity.JwtRequestEntity;
import com.shopme.jwtUtill.jwtEntity.JwtResponseEntity;

@Service
public class JwtUserDetaiService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtill jwtUtill;
	@Autowired
	private AuthenticationManager authenticateaManager;
	
	public JwtResponseEntity userLogin(JwtRequestEntity jwtRequest) throws Exception {
		String email = jwtRequest.getEmail();
		String password = jwtRequest.getPassword();

				authenticate(email, password);
			final 	UserDetails loadUserByUsername = loadUserByUsername(email);
			String generateToken = jwtUtill.generateToken(loadUserByUsername);
			User user = userRepository.findByEmail(email);
			return new JwtResponseEntity(user, generateToken);
	}
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		
		if(user !=null) {
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
		}
		else {
			throw new UsernameNotFoundException("User not found exception");
		}
		
	}
	
	private void authenticate(String email, String password) throws Exception {
		try{
			authenticateaManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		}catch (DisabledException e) {
			throw new Exception("User is disabled");
		}
		catch (BadCredentialsException e) {
			throw new Exception("Bad credentials from user");
		}
	}
	
	private Set getAuthorities(User user) {
		
		Set authorities = new HashSet<>();
		user.getRole().forEach(role->
		{
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
			}
		);
		return authorities;
		
	}

}
