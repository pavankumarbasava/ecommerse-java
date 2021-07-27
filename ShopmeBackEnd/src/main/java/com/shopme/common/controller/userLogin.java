package com.shopme.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopme.jwtUtill.jwtEntity.JwtRequestEntity;
import com.shopme.jwtUtill.jwtEntity.JwtResponseEntity;
import com.shopme.springSecurityConfig.JwtUserDetaiService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class userLogin {

	@Autowired
	private JwtUserDetaiService userDetailService;
	
	
	
	
	@PostMapping("/login")
	public JwtResponseEntity userLoginMethod(@RequestBody JwtRequestEntity jwtRequestEntity) throws Exception {
		return userDetailService.userLogin(jwtRequestEntity);
		
		
	}
	
}
