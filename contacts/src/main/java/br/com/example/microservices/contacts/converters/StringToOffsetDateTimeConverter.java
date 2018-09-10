package br.com.example.microservices.contacts.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Component
public class StringToOffsetDateTimeConverter implements Converter<String, OffsetDateTime> {

    @Autowired
    private DateToOffsetDateTimeConverter toOffsetDateTimeConverter;

    @Override
    public  OffsetDateTime convert(String source) {
        OffsetDateTime offSetDateTime = null;
        Date data = null;
        try {
            data = toDate(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        offSetDateTime = toOffsetDateTimeConverter.convert(data).withOffsetSameInstant(ZoneOffset.UTC);
        return offSetDateTime;
    }

    public Date toDate(String source) throws Exception {
        List<String> formatStrings = DateFormats.getValues();
        for (String formatString : formatStrings) {
            try {
                return new SimpleDateFormat(formatString).parse(source);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        throw new Exception("A data " + source + " está em um formato inválido.");
    }
}