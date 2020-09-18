package com.wj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wj.dao.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	@Query("select u from Utilisateur u where u.username like :x and u.password like :y")
	public Utilisateur chercherUser(@Param("x") String username, @Param("y") String password);

	Utilisateur findByEmailAndPassword(String email, String password);

}
