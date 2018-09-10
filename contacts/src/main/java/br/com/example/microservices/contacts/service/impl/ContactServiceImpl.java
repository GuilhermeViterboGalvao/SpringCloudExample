package br.com.example.microservices.contacts.service.impl;

import br.com.example.microservices.contacts.entity.Contact;
import br.com.example.microservices.contacts.entity.QContact;
import br.com.example.microservices.contacts.exception.ContactException;
import br.com.example.microservices.contacts.repository.primary.ContactPrimaryRepository;
import br.com.example.microservices.contacts.repository.secondary.ContactSecondaryRepository;
import br.com.example.microservices.contacts.service.ContactService;
import br.com.example.microservices.contacts.service.ValidateService;
import br.com.example.microservices.contacts.service.params.ContactFindAllParams;
import com.querydsl.core.BooleanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Guilherme on 09/09/2018.
 */
@Slf4j
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactPrimaryRepository contactPrimaryRepository;

    @Autowired
    private ContactSecondaryRepository contactSecondaryRepository;

    @Autowired
    private ValidateService validateService;

    @Override
    public Contact save(Contact contact) throws ContactException {
        log.info("save contact: {}", contact);
        validateService.validateSave(contact);
        return contactPrimaryRepository.save(contact);
    }

    @Override
    public void delete(Contact contact) throws ContactException {
        log.info("delete contact: {}", contact);
        validateService.validateDelete(contact);
        contactPrimaryRepository.delete(contact);
    }

    @Override
    public Optional<Contact> findById(Long id) {
        return contactSecondaryRepository.findById(id);
    }

    @Override
    public Page<Contact> findAll(ContactFindAllParams params) throws ContactException {
        validateService.validateParams(params);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (params.getEmail() != null && !params.getEmail().isEmpty()) {
            booleanBuilder.and(QContact.contact.email.equalsIgnoreCase(params.getEmail()));
        }
        if (params.getName() != null && !params.getName().isEmpty()) {
            booleanBuilder.and(QContact.contact.name.eq(params.getName()));
        }
        if (params.getPhone() != null && !params.getPhone().isEmpty()) {
            booleanBuilder.and(QContact.contact.name.eq(params.getPhone()));
        }
        log.info("findAll QUERY: {}", booleanBuilder.toString());
        log.info("findAll OFFSET={} and LIMIT={}", params.getOffset(), params.getLimit());
        Page<Contact> page = contactSecondaryRepository.findAll(booleanBuilder, PageRequest.of(params.getOffset(), params.getLimit()));
        log.info("findAll total of elements found {}.", page.getTotalElements());
        return page;
    }
}
