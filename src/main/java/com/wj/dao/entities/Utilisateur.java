package com.wj.dao.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "UTILISATEURS")
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String password;
	private String email;
	@ManyToMany(mappedBy = "utilisateurs")
	private Set<Role> roles = new HashSet<Role>();

	public Utilisateur() {
		super();
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
