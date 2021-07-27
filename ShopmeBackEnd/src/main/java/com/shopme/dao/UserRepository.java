package com.shopme.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.shopme.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

   Optional<User[]> findByFirstName(String name);
   User findByEmail(String email);
}
