package br.com.example.microservices.contacts.service;

/**
 * Created by Guilherme on 09/09/2018.
 */
public interface MongoSequenceService {

    long getNextId(String sequenceId);
}
