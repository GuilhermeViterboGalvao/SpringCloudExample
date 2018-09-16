package br.com.example.microservices.zuul;

import br.com.example.microservices.zuul.config.EurekaClientConfiguration;
import br.com.example.microservices.zuul.config.HystrixConfiguration;
import br.com.example.microservices.zuul.config.ModuleConfiguration;
import br.com.example.microservices.zuul.config.ZuulProxyConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by Guilherme on 09/09/2018.
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class App {

    public static void main(String[] args) {
        new SpringApplicationBuilder(
                App.class,
                ModuleConfiguration.class,
                HystrixConfiguration.class,
                EurekaClientConfiguration.class,
                ZuulProxyConfiguration.class
        ).run(args);
    }
}
