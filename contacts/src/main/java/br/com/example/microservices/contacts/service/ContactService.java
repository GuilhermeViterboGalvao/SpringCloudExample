package br.com.example.microservices.contacts.service;

import br.com.example.microservices.contacts.entity.Contact;
import br.com.example.microservices.contacts.exception.ContactException;
import br.com.example.microservices.contacts.service.params.ContactFindAllParams;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Created by Guilherme on 09/09/2018.
 */
public interface ContactService {

    Contact save(Contact contact) throws ContactException;

    void delete(Contact contact) throws ContactException;

    Optional<Contact> findById(Long id);

    Page<Contact> findAll(ContactFindAllParams params) throws ContactException;
}
