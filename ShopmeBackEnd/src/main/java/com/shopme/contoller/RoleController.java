package com.shopme.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopme.entity.Role;
import com.shopme.service.RoleService;



@RestController
public class RoleController {
   @Autowired
	private RoleService roleService;
	
	@PostMapping({"/createNewRole"})
	public ResponseEntity<Role>  createNewrole(@RequestBody Role role) {
		 Role createNewRole = roleService.createNewRole(role);
		 return new ResponseEntity<>(createNewRole, HttpStatus.CREATED);
	}
	
	@GetMapping({"/all"})
	public ResponseEntity<List<Role>>  getAllRoles() {
		
		List<Role> allRoles = roleService.getAllRoles();
		return new ResponseEntity<>(allRoles,HttpStatus.OK);
	}
	
	@PutMapping({"/update"})
	public ResponseEntity<Role>  Updaterole(@RequestBody Role role) {
		 Role updateRole = roleService.updateRole(role);
		 return new ResponseEntity<>(updateRole, HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Role> getRole(Integer id){
		 Role getRole = roleService.findRoleById(id);
		 return new ResponseEntity<>(getRole, HttpStatus.OK);
		
	} 
}
