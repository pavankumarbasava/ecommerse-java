package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.shopme.dao.RoleRepository;
import com.shopme.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class RoleRepositoryTest {

	
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Test
	public void createUser() {
	//Role role= new Role("Admin", "Manage everything");
//	Role savedrole = roleRepository.save(role);
	
//	assertThat(savedrole.getId()).isGreaterThan(0);
	}	
	
}
