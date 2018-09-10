package br.com.example.microservices.contacts.config.mongo;

import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

@Configuration
@DependsOn("mongodProcess")
@EnableMongoRepositories(basePackages = "br.com.example.microservices.contacts.repository.primary", mongoTemplateRef = "primaryMongoTemplate")
public class PrimaryMongoConnection extends AbstractMongoConnection {

    @Value("${mongo.primary.uri}")
    private String primaryMongoUri;

    @Value("${mongo.primary.socketTimeout}")
    private int socketTimeout;

    @Value("${mongo.primary.connectTimeout}")
    private int connectTimeout;

    @Value("${mongo.primary.minHeartbeatFrequency}")
    private int minHeartbeatFrequency;

    @Value("${mongo.primary.heartbeatSocketTimeout}")
    private int heartbeatSocketTimeout;

    @Override
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate getMongoTemplate() throws UnknownHostException {
        MongoTemplate mongoTemplate = new MongoTemplate(createMongoDbFactory());

        // Para mais informações sobre o padrão de leitura e escrita no Mongo
        // https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo-template.writeconcernresolver
        mongoTemplate.setWriteConcern(WriteConcern.JOURNALED);

        MappingMongoConverter mappingMongoConverter = (MappingMongoConverter)mongoTemplate.getConverter();
        mappingMongoConverter.setCustomConversions(customConversions());
        mappingMongoConverter.afterPropertiesSet();

        createArticleSequence(mongoTemplate);

        return mongoTemplate;
    }

    private void createArticleSequence(MongoTemplate mongoTemplate) {
        MongoCollection jpublisherSequences =  mongoTemplate.getCollection("contactsSequences");
        Object articleSequence = jpublisherSequences.find(Filters.eq("id", "contactSequence")).first();
        if (articleSequence == null) {
            jpublisherSequences.insertOne(new Document().append("id", "contactSequence").append("seq", 1));
        }
    }

    @Override
    public String getMongoUri() {
        return primaryMongoUri;
    }

    @Override
    public int getSocketTimeout() {
        return socketTimeout;
    }

    @Override
    public int getConnectTimeout() {
        return connectTimeout;
    }

    @Override
    public int getMinHeartbeatFrequency() {
        return minHeartbeatFrequency;
    }

    @Override
    public int getHeartbeatSocketTimeout() {
        return heartbeatSocketTimeout;
    }
}