package br.com.example.microservices.eureka;

import br.com.example.microservices.eureka.config.EurekaServerConfiguration;
import br.com.example.microservices.eureka.config.ModuleConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.hystrix.HystrixAutoConfiguration;

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
                HystrixAutoConfiguration.class,
                EurekaServerConfiguration.class
        ).run(args);
    }
}
