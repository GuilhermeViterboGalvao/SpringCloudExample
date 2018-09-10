package br.com.example.microservices.contacts.config.mongo;

import br.com.example.microservices.contacts.converters.DateToOffsetDateTimeConverter;
import br.com.example.microservices.contacts.converters.OffsetDateTimeToDateConverter;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.bson.types.Decimal128;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMongoConnection {

    public MongoDbFactory createMongoDbFactory() throws UnknownHostException {
        MongoClientOptions.Builder builder = MongoClientOptions.builder();

        if (getSocketTimeout() > 0) {
            builder.socketTimeout(getSocketTimeout());
        }

        if (getConnectTimeout() > 0) {
            builder.connectTimeout(getConnectTimeout());
        }

        if (getMinHeartbeatFrequency() > 0) {
            builder.minHeartbeatFrequency(getMinHeartbeatFrequency());
        }

        if (getHeartbeatSocketTimeout() > 0) {
            builder.heartbeatSocketTimeout(getHeartbeatSocketTimeout());
        }

        MongoClientURI mongoClientURI = new MongoClientURI(getMongoUri(), builder);

        return new SimpleMongoDbFactory(mongoClientURI);
    }

    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();

        converters.add(new Converter<Decimal128, BigDecimal>() {

            @Override
            public BigDecimal convert(Decimal128 s) {
                return s == null ? null : s.bigDecimalValue();
            }

        });

        converters.add(new Converter<BigDecimal, Decimal128>() {

            @Override
            public Decimal128 convert(BigDecimal s) {
                return s == null ? null : new Decimal128(s);
            }

        });

        converters.add(new DateToOffsetDateTimeConverter());
        converters.add(new OffsetDateTimeToDateConverter());

        return new MongoCustomConversions(converters);
    }

    public abstract MongoTemplate getMongoTemplate() throws UnknownHostException;

    public abstract String getMongoUri();

    public abstract int getSocketTimeout();

    public abstract int getConnectTimeout();

    public abstract int getMinHeartbeatFrequency();

    public abstract int getHeartbeatSocketTimeout();
}
