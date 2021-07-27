package com.shopme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopme.dao.UserRepository;
import com.shopme.entity.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerNewUser(User user) {
		String password = user.getPassword();
		String encodedPassword=getEncodedPassword(password);
		user.setPassword(encodedPassword);
		return userRepository.save(user);
	}

	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	public User updateNewUser(User user) {
		String password = user.getPassword();
		getEncodedPassword(password);
		user.setPassword(password);
		return userRepository.save(user);
	}

	public void deleteUser(Integer id) {
		userRepository.deleteById(id);

	}

	public User[] findUsers(String name) {
		Optional<User[]> findByFirstName = userRepository.findByFirstName(name);
		return findByFirstName.orElseThrow(IllegalArgumentException::new);
	}

	public User findUserById(Integer id) {
		Optional<User> findUseById = userRepository.findById(id);
		return findUseById.orElseThrow(IllegalArgumentException::new);
	}

	
	private String getEncodedPassword(String password) {
		//return password;
		return passwordEncoder.encode(password);
	}

}
