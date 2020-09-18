package com.wj.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wj.dao.ContactRepository;
import com.wj.dao.entities.Contact;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ContactRestService {

	@Autowired
	private ContactRepository contactRepository;

	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public List<Contact> getContacts() {
		return contactRepository.findAll();
	}

	@PostMapping("/contact/ajout")
	public ResponseEntity createContact(@RequestBody Contact contact) {
		if (contact == null) {
			return ResponseEntity.badRequest().body("Cannot create contact with empty fields");
		}
		Contact createdUser = contactRepository.save(contact);
		return ResponseEntity.ok(createdUser);
	}

	// @PathVariable indique que le paramètre d'une méthode est lié à l'URI
	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.GET)
	public Contact getContact(@PathVariable Long id) {
		return contactRepository.findOne(id);
	}

	@RequestMapping(value = "/chercherContacts", method = RequestMethod.GET)
	public Page<Contact> chercher(@RequestParam(name = "mc", defaultValue = "") String mc,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "3") int size) {

		return contactRepository.chercher("%" + mc + "%", new PageRequest(page, size));
	}

	/*
	 * Ici il recupère la requête http en format json et le stock ensuite dans
	 * l'objet contact
	 */
	@RequestMapping(value = "/contacts/save", method = RequestMethod.POST)
	public Contact saveContact(@RequestBody Contact c) {
		return contactRepository.save(c);
	}

//	@RequestMapping(value = "/contacts/delete/{id}", method = RequestMethod.DELETE)
//	public void deleteContact(@PathVariable Long id) {
//		contactRepository.delete(id);
//	}

	@DeleteMapping("/contact/delete/{idContact}")
	public ResponseEntity deleteContact(@PathVariable(name = "idContact") Long idContact) {
		if (idContact == null) {
			return ResponseEntity.badRequest().body("Cannot remove contact with null ID");
		}
		Contact contact = contactRepository.getOne(idContact);
		if (contact == null) {
			return ResponseEntity.notFound().build();
		}
		contactRepository.delete(contact);
		return ResponseEntity.ok("Contact removed with success");
	}

	/*
	 * Je mets à jour le contact dont l'id est connu
	 */
//	@RequestMapping(value = "/contacts/update/{id}", method = RequestMethod.PUT)
//	public void updateContact(@RequestBody Contact c, @PathVariable Long id) {
//		c.setId(id);
//		contactRepository.save(c);
//	}

	@PutMapping("/contacts/update/{idContact}")
	public ResponseEntity updateContact(@PathVariable(name = "idContact") Long idContact) {
		if (idContact == null) {
			return ResponseEntity.badRequest().body("Cannot update contact with null ID");
		}
		Contact contact = contactRepository.getOne(idContact);
		if (contact == null) {
			return ResponseEntity.notFound().build();
		}

		Contact contactToSave = new Contact();
		contactToSave.setDateNaissance(contact.getDateNaissance());
		contactToSave.setEmail(contact.getEmail());
		contactToSave.setNom(contact.getNom());
		contactToSave.setPhoto(contact.getPhoto());
		contactToSave.setPrenom(contact.getPrenom());
		contactToSave.setTel(contact.getTel());
		contactToSave.setId(contact.getId());

		contactRepository.save(contactToSave);
		return ResponseEntity.ok("Contact updated with success");
	}

	@GetMapping("contact/{idContact}")
	public ResponseEntity findContactById(@PathVariable(name = "idContact") Long idContact) {
		if (idContact == null) {
			return ResponseEntity.badRequest().body("Cannot find contact with null ID");
		}
		Contact contact = contactRepository.getOne(idContact);
		if (contact == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(contact);
	}
}
