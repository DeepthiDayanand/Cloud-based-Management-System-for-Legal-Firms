package com.capstonelegal.contact.repository;

import com.capstonelegal.contact.model.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface ContactRepository  extends JpaRepository<Contact, String>, JpaSpecificationExecutor<Contact> {
}
