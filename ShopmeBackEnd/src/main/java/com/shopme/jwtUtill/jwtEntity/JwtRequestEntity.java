package com.shopme.jwtUtill.jwtEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequestEntity {

	
	private String email;
	private String password;
	
}


