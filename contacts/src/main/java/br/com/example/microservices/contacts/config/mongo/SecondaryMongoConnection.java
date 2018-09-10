package br.com.example.microservices.contacts.config.mongo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

@Configuration
@EnableMongoRepositories(basePackages = { "br.com.example.microservices.contacts.repository.secondary" }, mongoTemplateRef = "secondaryMongoTemplate")
public class SecondaryMongoConnection extends AbstractMongoConnection {

    @Value("${mongo.secondary.uri}")
    private String defaultMongoUri;

    @Value("${mongo.secondary.socketTimeout}")
    private int socketTimeout;

    @Value("${mongo.secondary.connectTimeout}")
    private int connectTimeout;

    @Value("${mongo.secondary.minHeartbeatFrequency}")
    private int minHeartbeatFrequency;

    @Value("${mongo.secondary.heartbeatSocketTimeout}")
    private int heartbeatSocketTimeout;

    @Override
    @Bean(name = "secondaryMongoTemplate")
    public MongoTemplate getMongoTemplate() throws UnknownHostException {
        MongoTemplate mongoTemplate = new MongoTemplate(createMongoDbFactory());

        MappingMongoConverter mappingMongoConverter = (MappingMongoConverter) mongoTemplate.getConverter();
        mappingMongoConverter.setCustomConversions(customConversions());
        mappingMongoConverter.afterPropertiesSet();

        return mongoTemplate;
    }

    @Override
    public String getMongoUri() {
        return defaultMongoUri;
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
