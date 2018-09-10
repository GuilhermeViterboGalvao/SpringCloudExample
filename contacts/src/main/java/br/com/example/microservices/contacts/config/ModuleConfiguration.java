package br.com.example.microservices.contacts.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "br.com.example.microservices.contacts" })
public class ModuleConfiguration { }