package br.com.example.microservices.contacts.converters;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.OffsetDateTime;

@Component
public class CustomOffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {

    @Autowired
    private StringToOffsetDateTimeConverter toOffsetDateTimeConverter;

    @Override
    public OffsetDateTime deserialize(
        final JsonParser jsonParser,
        final DeserializationContext ctxt
    ) throws IOException {
        return toOffsetDateTimeConverter.convert(jsonParser.getValueAsString());
    }
}
