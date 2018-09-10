package br.com.example.microservices.zuul.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "br.com.example.microservices.zuul" })
public class ModuleConfiguration { }