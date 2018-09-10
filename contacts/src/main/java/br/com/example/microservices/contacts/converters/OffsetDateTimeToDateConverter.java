package br.com.example.microservices.contacts.converters;

import io.micrometer.core.lang.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Date;

@Component
public class OffsetDateTimeToDateConverter implements Converter<OffsetDateTime, Date> {

    @Nullable
    @Override
    public Date convert(OffsetDateTime source) {
        return source == null ? null : Date.from(source.toInstant());
    }

}