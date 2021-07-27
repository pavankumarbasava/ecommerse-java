package com.shopme.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 128, nullable=false)
	private String email;
	
	@Column(name="first_name", length = 45, nullable=false)
	private String firstName;
	
	@Column(name="last_name", length = 45, nullable=false)
	private String lastName;
	
	@Column(length = 64, nullable=false)
	private String password;
	
	@Column(length = 64)
	private String photo;
	
	private boolean enable;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH})
	@JoinTable(name="user_roles",
	        joinColumns = @JoinColumn(name="user_id"),
	        inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> role = new HashSet<>();
	
	public void addRole(Role role) {
		this.role.add(role);
	}


  
}
