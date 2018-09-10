package br.com.example.microservices.contacts.service;

import br.com.example.microservices.contacts.entity.Contact;
import br.com.example.microservices.contacts.exception.ContactException;
import br.com.example.microservices.contacts.service.params.ContactFindAllParams;

/**
 * Created by Guilherme on 09/09/2018.
 */
public interface ValidateService {

    void validateParams(ContactFindAllParams params) throws ContactException;

    void validateSave(Contact contact) throws ContactException;

    void validateDelete(Contact contact) throws ContactException;
}
