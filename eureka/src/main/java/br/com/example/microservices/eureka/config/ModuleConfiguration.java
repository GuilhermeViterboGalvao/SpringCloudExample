package br.com.example.microservices.eureka.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "br.com.example.microservices.eureka" })
@EnableAutoConfiguration(exclude = {FreeMarkerAutoConfiguration.class, SecurityAutoConfiguration.class })
public class ModuleConfiguration { }