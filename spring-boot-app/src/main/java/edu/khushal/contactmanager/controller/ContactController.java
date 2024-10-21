package edu.khushal.contactmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.khushal.contactmanager.entity.Contact;
import edu.khushal.contactmanager.entity.ContactResponse;
import edu.khushal.contactmanager.service.ContactService;

@RestController
@CrossOrigin(origins = "*")

public class ContactController {

	@Autowired
	private ContactService contactService;

	@PostMapping(value = "/contact")
	protected ResponseEntity<ContactResponse<Contact>> addContact(@RequestBody Contact contact) {
		Contact contact2 = contactService.addContact(contact);
		ContactResponse<Contact> response = new ContactResponse<>();
		response.setMassage("contact is added");
		response.setHttpStatusCode(HttpStatus.CREATED.value());
		response.setData(contact2);
		return new ResponseEntity<ContactResponse<Contact>>(response, HttpStatus.CREATED);

	}

//	@GetMapping(value = "/contacts")
//	protected ResponseEntity<ContactResponse<List<Contact>>> findAllContacts () {
//		List<Contact> allContacts = contactService.findAllContacts();
//		ContactResponse<List<Contact>> response=new ContactResponse<>();
//		if (allContacts.size()>0) {
//			response.setMassage("conacts found");
//			response.setHttpStatusCode(HttpStatus.FOUND.value());
//			response.setData(allContacts);
//			return new ResponseEntity<ContactResponse<List<Contact>>>(response, HttpStatus.FOUND);
//			
//		} else {
//			response.setMassage("conacts are not availables ");
//			response.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
//			
//			return new ResponseEntity<ContactResponse<List<Contact>>>(response, HttpStatus.NOT_FOUND);
//			
//
//		}
//		
//	}

	@GetMapping(value = "/contacts")
	protected List<Contact> findAllContacts() {
		List<Contact> allContacts = contactService.findAllContacts();
		return allContacts;

	}

	@PatchMapping(value = "/contact")
	protected ResponseEntity<ContactResponse<Contact>> updateContact(@RequestBody Contact contact) {
		Contact updatedContact = contactService.updateContact(contact);
		ContactResponse<Contact> response = new ContactResponse<>();
		response.setMassage("contact is updated");
		response.setHttpStatusCode(HttpStatus.CREATED.value());
		response.setData(updatedContact);
		return new ResponseEntity<ContactResponse<Contact>>(response, HttpStatus.CREATED);

	}

//	@GetMapping(value = "/contact/{id}")
//	protected ResponseEntity<ContactResponse<Contact>>  findContactById(@PathVariable(name = "id") int contactid) {
//		Contact contact = contactService.findContactById(contactid);
//		ContactResponse<Contact> response=new ContactResponse<>();
//		if (contact!=null) {
//			response.setMassage("conact found");
//			response.setHttpStatusCode(HttpStatus.FOUND.value());
//			response.setData(contact);
//			return new ResponseEntity<ContactResponse<Contact>>(response, HttpStatus.FOUND);
//			
//		} else {
//			response.setMassage("conacts are not availables found");
//			response.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
//			
//			return new ResponseEntity<ContactResponse<Contact>>(response, HttpStatus.NOT_FOUND);
//			
//
//		}
//	}

	@GetMapping(value = "/contact/{id}")
	protected Contact findContactById(@PathVariable(name = "id") int contactid) {
		Contact contact = contactService.findContactById(contactid);

		if (contact != null) {

			return contact;

		} else {

			return null;

		}
	}

	@DeleteMapping(value = "/contact/{id}")
	protected ResponseEntity<ContactResponse<Contact>> deleteContact(@PathVariable(name = "id") int contactid) {
		Contact deletedContact = contactService.deleteContact(contactid);
		ContactResponse<Contact> response = new ContactResponse<>();
		response.setMassage("contact is deleted");
		response.setHttpStatusCode(HttpStatus.OK.value());
		response.setData(deletedContact);
		return new ResponseEntity<ContactResponse<Contact>>(response, HttpStatus.OK);

	}

}
