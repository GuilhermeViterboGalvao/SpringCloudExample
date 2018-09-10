package br.com.example.microservices.zuul.config;

import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableZuulProxy
public class ZuulProxyConfiguration { }