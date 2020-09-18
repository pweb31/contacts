package com.wj.dao.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "ROLES")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "UTILISATEURS_ROLES", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "UTILISATEUR_ID"))
	private Set<Utilisateur> utilisateurs = new HashSet<Utilisateur>();

	public Role(@JsonProperty("name") String name) {
		this.name = name;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void addUtilisateurs(Utilisateur user) {
		this.utilisateurs.add(user);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public Set<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "UTILISATEURS_ROLES", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "UTILISATEUR_ID"))
	public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

}
