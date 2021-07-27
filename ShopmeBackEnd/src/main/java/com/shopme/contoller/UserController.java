package com.shopme.contoller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopme.entity.Role;
import com.shopme.entity.User;
import com.shopme.service.RoleService;
import com.shopme.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	

	@PostMapping({ "/createUser" })
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<User> registerNewUser(@RequestBody User user) {

	
		Set<Role> userRoles = user.getRole();
		Set<Role> collectedUseRoles = userRoles.stream().map(role -> roleService.findRoleById(role.getId()))
				.collect(Collectors.toSet());

		user.setRole(collectedUseRoles);
		User registerNewUser = userService.registerNewUser(user);
		return new ResponseEntity<User>(registerNewUser, HttpStatus.CREATED);

	}

	@GetMapping({ "/allUsers" })
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> getAllUsers = userService.getAllUsers();
		return new ResponseEntity<>(getAllUsers, HttpStatus.OK);

	}
	

	@PutMapping({ "/updateUser" })
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<User> updateNewUser(@RequestBody User user) {
		Set<Role> userRoles = user.getRole();
		Set<Role> collectedUseRoles = userRoles.stream().map(role -> roleService.findRoleById(role.getId()))
				.collect(Collectors.toSet());

		user.setRole(collectedUseRoles);

		User updatedUser = userService.updateNewUser(user);
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);

	}

	@DeleteMapping({ "/deleteUser/{id}" })
	@PreAuthorize("hasRole('Admin')")
	public void deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);

	} 

	@GetMapping({ "/find" })
	public ResponseEntity<User[]> findUsers(@RequestParam String name) {
		User[] findUsers = userService.findUsers(name);
		return new ResponseEntity<>(findUsers, HttpStatus.OK);

	}

}
