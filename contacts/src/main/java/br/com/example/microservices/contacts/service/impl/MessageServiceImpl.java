package br.com.example.microservices.contacts.service.impl;

import br.com.example.microservices.contacts.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Created by Guilherme on 09/09/2018.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageSource messages;

    @Override
    public String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messages.getMessage(key, null, locale);
    }

    @Override
    public String getMessage(String key, Object... attrs) {
        Locale locale = LocaleContextHolder.getLocale();
        return messages.getMessage(key, attrs, locale);
    }

    @Override
    public String getMessage(String key, Locale locale) {
        return messages.getMessage(key, null, locale);
    }
}
