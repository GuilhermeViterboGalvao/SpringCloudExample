package br.com.example.microservices.contacts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Guilherme on 09/09/2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document(collection = "contact")
public class Contact {

    @Id
    private Long id;

    private  String name;

    private String email;

    private String phone;
}
