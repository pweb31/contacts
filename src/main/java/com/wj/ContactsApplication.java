package com.wj;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wj.dao.ContactRepository;
import com.wj.dao.RoleRepository;
import com.wj.dao.UtilisateurRepository;
import com.wj.dao.entities.Contact;
import com.wj.dao.entities.Role;
import com.wj.dao.entities.Utilisateur;

@SpringBootApplication
public class ContactsApplication implements CommandLineRunner {

	@Autowired
	ContactRepository contactRepository;

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ContactsApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		/*
		 * Pour spécifier la valeur d'une date on crée un objet DateFormat
		 * 
		 */
		/*
		 * DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 * contactRepository.save(new Contact("JUSTE", "Mathéo", df.parse("2018-02-07")
		 * , "matheo@gmail.com", "0625364856", "matheo.jpeg"));
		 * contactRepository.save(new Contact("JUSTE", "Louis", df.parse("2013-02-13") ,
		 * "louis@gmail.com", "0665364725", "louis.jpeg")); contactRepository.save(new
		 * Contact("JUSTE", "Woodson", df.parse("1984-05-18") , "woodson@gmail.com",
		 * "0685426414", "woodson.jpeg")); contactRepository.save(new
		 * Contact("SALANSON", "Flora", df.parse("1994-03-06") , "flora@gmail.com",
		 * "0675436842", "flora.jpeg")); contactRepository.save(new Contact("Trujillo",
		 * "Aurore", df.parse("1988-10-02") , "aurore@gmail.com", "0648766234",
		 * "aurore.jpeg"));
		 * 
		 * contactRepository.findAll().forEach(c -> { System.out.println(c); });
		 */

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		contactRepository.save(new Contact("JUSTE", "Mathéo", df.parse("2018-02-07"), "matheo@gmail.com", "0625364856",
				"matheo.jpeg"));
		contactRepository.save(
				new Contact("JUSTE", "Louis", df.parse("2013-02-13"), "louis@gmail.com", "0665364725", "louis.jpeg"));
		contactRepository.save(new Contact("JUSTE", "Woodson", df.parse("1984-05-18"), "woodson@gmail.com",
				"0685426414", "woodson.jpeg"));
		contactRepository.save(new Contact("SALANSON", "Flora", df.parse("1994-03-06"), "flora@gmail.com", "0675436842",
				"flora.jpeg"));
		contactRepository.save(new Contact("Trujillo", "Aurore", df.parse("1988-10-02"), "aurore@gmail.com",
				"0648766234", "aurore.jpeg"));

		Utilisateur user1 = new Utilisateur();
		user1.setEmail("jl@mymail1.com");
		user1.setPassword("mypass1");
		user1.setUsername("myUser1");
		Role role1 = new Role("ADMIN");
		roleRepository.save(role1);
		role1.addUtilisateurs(user1);
		Set<Role> userRole1 = new HashSet<Role>();
		userRole1.add(role1);
		user1.addRole(role1);
		utilisateurRepository.save(user1);

		Utilisateur user2 = new Utilisateur();
		user2.setEmail("jl@mymail2.com");
		user2.setPassword("mypass2");
		user2.setUsername("myUser2");
		Role role2 = new Role("USER");
		roleRepository.save(role2);
		role2.addUtilisateurs(user2);
		Set<Role> userRole2 = new HashSet<Role>();
		userRole2.add(role2);
		user2.setRoles(userRole2);
		user2.addRole(role2);
		utilisateurRepository.save(user2);

	}

}
