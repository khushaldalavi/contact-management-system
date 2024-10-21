package edu.khushal.contactmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.khushal.contactmanager.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
