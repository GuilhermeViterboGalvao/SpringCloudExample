package br.com.example.microservices.contacts.config;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongoCmdOptionsBuilder;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Created by Guilherme on 09/09/2018.
 */
@Configuration
public class EmbeddedMongoDbConfiguration {

    @Value("${embedded.mongodb.host}")
    private String host;

    @Value("${embedded.mongodb.port}")
    private int port;

    @Bean
    public MongodProcess mongodProcess() throws IOException {
        MongodStarter starter = MongodStarter.getDefaultInstance();
        MongodExecutable executable = starter.prepare(
            new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(host, port, Network.localhostIsIPv6()))
                .cmdOptions(new MongoCmdOptionsBuilder().useNoJournal(false).build())
                .build()
        );
        return executable.start();
    }
}
