package com.shopme.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.shopme.dao.RoleRepository;
import com.shopme.entity.Role;

import java.util.List;
import java.util.Optional;
@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Role createNewRole(Role role) {
	
	return roleRepository.save(role);

	}

	public List<Role> getAllRoles() {
		
		return roleRepository.findAll();
	}

	public Role updateRole(Role role) {
		return roleRepository.save(role);//roleRepository.updateRole(role);
	}	
	
	public Role findRoleById(Integer id) {
	 Optional<Role> findById = roleRepository.findById(id);
		return findById.orElseThrow(IllegalArgumentException::new);

	}
}
