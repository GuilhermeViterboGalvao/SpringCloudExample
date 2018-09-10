package br.com.example.microservices.contacts.exception;

/**
 * Created by Guilherme on 09/09/2018.
 */
public class ContactException extends Exception {

    public ContactException(String message) {
        super(message);
    }
}
