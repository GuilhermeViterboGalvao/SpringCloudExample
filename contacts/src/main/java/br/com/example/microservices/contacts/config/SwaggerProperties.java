package br.com.example.microservices.contacts.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Guilherme on 09/09/2018.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    private String pathMapping;

    private String basePackage;

    private String title;

    private String description;

    private String version;
}
