package br.com.example.microservices.contacts;

import br.com.example.microservices.contacts.config.*;
import br.com.example.microservices.contacts.config.mongo.PrimaryMongoConnection;
import br.com.example.microservices.contacts.config.mongo.SecondaryMongoConnection;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by Guilherme on 09/09/2018.
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        FreeMarkerAutoConfiguration.class,
        SecurityAutoConfiguration.class,
        EmbeddedMongoAutoConfiguration.class,

        // Como as configurações do Mongo estão sendo manuais
        // por conta de termos a necessidade de ter duas conexões,
        // devemos remover os "starters" do Mongo no Spring.
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class
})
public class App {

    public static void main(String... args) {
        new SpringApplicationBuilder(
            App.class,
            ModuleConfiguration.class,
            EmbeddedMongoDbConfiguration.class,
            SecondaryMongoConnection.class,
            PrimaryMongoConnection.class,
            SwaggerConfiguration.class,
            EurekaClientConfiguration.class,
            JacksonConfiguration.class
        ).run(args);
    }
}
