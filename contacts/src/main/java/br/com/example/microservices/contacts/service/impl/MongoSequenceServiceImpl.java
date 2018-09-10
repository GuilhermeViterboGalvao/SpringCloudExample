package br.com.example.microservices.contacts.service.impl;

import br.com.example.microservices.contacts.config.mongo.MongoSequences;
import br.com.example.microservices.contacts.service.MongoSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by Guilherme on 09/09/2018.
 */
@Service
public class MongoSequenceServiceImpl implements MongoSequenceService {

    @Autowired
    @Qualifier("primaryMongoTemplate")
    private MongoTemplate mongo;

    @Override
    public long getNextId(String sequenceId) {
        MongoSequences mongoSequences = mongo.findAndModify(
                query(where("id").is(sequenceId)),
                new Update().inc("seq", 1),
                options().returnNew(true).upsert(true),
                MongoSequences.class
        );
        return mongoSequences.getSeq();
    }
}
