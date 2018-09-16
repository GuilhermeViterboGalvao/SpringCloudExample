package br.com.example.microservices.zuul.config;

import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Guilherme on 16/09/2018.
 */
@Configuration
@EnableHystrix
@EnableHystrixDashboard
public class HystrixConfiguration { }