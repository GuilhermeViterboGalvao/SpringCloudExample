package br.com.example.microservices.contacts.service.impl;

import br.com.example.microservices.contacts.entity.Contact;
import br.com.example.microservices.contacts.exception.ContactException;
import br.com.example.microservices.contacts.service.MessageService;
import br.com.example.microservices.contacts.service.ValidateService;
import br.com.example.microservices.contacts.service.params.ContactFindAllParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Guilherme on 09/09/2018.
 */
@Service
public class ValidateServiceImpl implements ValidateService {

    @Autowired
    private MessageService messageService;

    @Override
    public void validateParams(ContactFindAllParams params) throws ContactException {
        if (params == null) {
            throw new ContactException(messageService.getMessage("validate.params.null_or_empty"));
        } else if (params.getPhone() != null && params.getPhone().length() <= 8) {
            throw new ContactException(messageService.getMessage("validate.phone.length_must_be_more_then_8_digits"));
        } else if (params.getName() != null && params.getName().length() <= 2) {
            throw new ContactException(messageService.getMessage("validate.name.length_must_be_more_then_3_characters"));
        } else if (params.getEmail() != null && !params.getEmail().contains("@")) {
            throw new ContactException(messageService.getMessage("validate.email.invalid_email_format"));
        }
    }

    @Override
    public void validateSave(Contact contact) throws ContactException {
        if (contact == null) {
            throw new ContactException(messageService.getMessage("validate.contact.null_or_empty"));
        } else if (contact.getEmail() != null && !contact.getEmail().contains("@")) {
            throw new ContactException(messageService.getMessage("validate.email.invalid_email_format"));
        } else if (contact.getName() != null && contact.getName().length() <= 2) {
            throw new ContactException(messageService.getMessage("validate.name.length_must_be_more_then_3_characters"));
        } else if (contact.getPhone() != null && contact.getPhone().length() <= 8) {
            throw new ContactException(messageService.getMessage("validate.phone.length_must_be_more_then_8_digits"));
        }
    }

    @Override
    public void validateDelete(Contact contact) throws ContactException {
        if (contact == null) {
            throw new ContactException(messageService.getMessage("validate.contact.null_or_empty"));
        } else if (contact.getId() <= 0) {
            throw new ContactException(messageService.getMessage("validate.contact.id.is_less_then_0"));
        }
    }
}
