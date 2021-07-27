package com.shopme.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopme.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

//	@Modifying
//	@Query(Update role r SET r.name=)
//	Role updateRole(Role role);

	 
}
