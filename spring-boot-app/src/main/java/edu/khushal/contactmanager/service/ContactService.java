package edu.khushal.contactmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.khushal.contactmanager.entity.Contact;
import edu.khushal.contactmanager.repository.ContactRepository;

@Service
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;

	public Contact addContact(Contact contact) {
		return contactRepository.save(contact);
		
	}

	public List<Contact> findAllContacts() {
		List<Contact> contacts = contactRepository.findAll();
		return contacts;
		
	}

	public Contact updateContact(Contact contact) {
		return contactRepository.save(contact);
		
	}

	public Contact findContactById(int contactid) {
		Contact contact = contactRepository.findById(contactid).get();
		return contact;
		
	}

	public Contact deleteContact(int contactid) {
		Contact contact = contactRepository.findById(contactid).get();
		contactRepository.delete(contact);
		return contact;
		
	}

}
