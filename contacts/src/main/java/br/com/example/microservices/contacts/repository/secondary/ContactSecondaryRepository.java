package br.com.example.microservices.contacts.repository.secondary;

import br.com.example.microservices.contacts.entity.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * Created by Guilherme on 09/09/2018.
 */
public interface ContactSecondaryRepository extends MongoRepository<Contact, Long>, QuerydslPredicateExecutor<Contact> { }