package br.com.example.microservices.contacts.converters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CustomOffsetDateTimeSerializer extends JsonSerializer<OffsetDateTime> {

    private  DateTimeFormatter dateTimeFormatter;

    public CustomOffsetDateTimeSerializer(final DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public void serialize(
        final OffsetDateTime value,
        final JsonGenerator gen,
        final SerializerProvider serializers
    ) throws IOException {
        gen.writeString(dateTimeFormatter.format(value));
    }

}