package br.com.example.microservices.config;

import br.com.example.microservices.config.config.ConfigServerConfiguration;
import br.com.example.microservices.config.config.EurekaClientConfiguration;
import br.com.example.microservices.config.config.ModuleConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by Guilherme on 09/09/2018.
 */

@SpringBootApplication
@EnableAutoConfiguration
public class App {

    public static void main(String[] args) {
        new SpringApplicationBuilder(
                App.class,
                ModuleConfiguration.class,
                EurekaClientConfiguration.class,
                ConfigServerConfiguration.class
        ).run(args);
    }
}