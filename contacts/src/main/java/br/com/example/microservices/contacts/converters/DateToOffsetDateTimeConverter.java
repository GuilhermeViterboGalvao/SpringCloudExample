package br.com.example.microservices.contacts.converters;

import io.micrometer.core.lang.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Converter utilizado pelo Spring quando temos um @RequestParam do tipo
 * */
@Component
public class DateToOffsetDateTimeConverter implements Converter<Date, OffsetDateTime> {

    @Nullable
    @Override
    public OffsetDateTime convert(Date source) {
        return source == null ? null : OffsetDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
    }

}