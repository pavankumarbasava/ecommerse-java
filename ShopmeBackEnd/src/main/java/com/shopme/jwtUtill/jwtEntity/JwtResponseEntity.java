package com.shopme.jwtUtill.jwtEntity;

import com.shopme.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponseEntity {
  private User user;
  private String jwtToken;
  
}
