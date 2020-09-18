package com.wj.dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Contact implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nom;
	private String prenom;

	/*
	 * Avec cette annotation, on ne stock que la date On ne stock pas l'heure, la
	 * minute et la seconde
	 */
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;

	private String email;
	private String tel;

	/*
	 * On peut stocker les photos en base de données en format string en utilisant
	 * base 64. Ici on va stoker que le chemin
	 */
	private String photo;

	public Contact() {
		super();
	}

	public Contact(String nom, String prenom, Date dateNaissance, String email, String tel, String photo) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.email = email;
		this.tel = tel;
		this.photo = photo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance
				+ ", email=" + email + ", tel=" + tel + ", photo=" + photo + "]";
	}

}
