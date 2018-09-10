package br.com.example.microservices.contacts.service;

import java.util.Locale;

/**
 * Created by Guilherme on 09/09/2018.
 */
public interface MessageService {

    String getMessage(String key);

    String getMessage(String key, Object... attrs);

    String getMessage(String key, Locale locale);
}
