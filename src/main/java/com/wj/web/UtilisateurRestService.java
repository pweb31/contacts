package com.wj.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wj.dao.UtilisateurRepository;
import com.wj.dao.entities.Utilisateur;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UtilisateurRestService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<Utilisateur> getUtilisateurs() {
		return utilisateurRepository.findAll();
	}

	@PostMapping("/login")
	public ResponseEntity login(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password) {
		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
			return ResponseEntity.badRequest().body("Cannot login with empty user mail or password");
		}
		Utilisateur authenticatedUser = utilisateurRepository.findByEmailAndPassword(email, password);
		if (authenticatedUser == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(authenticatedUser);
	}

}
