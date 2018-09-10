package br.com.example.microservices.contacts.service.params;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by Guilherme on 09/09/2018.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ContactFindAllParams {

    @ApiParam(value = "name", example = "John Doe")
    private String name;

    @ApiParam(value = "email", example = "some_one@gmail.com")
    private String email;

    @ApiParam(value = "phone", example = "+55 11 98877 - 1568")
    private String phone;

    @NotNull
    @ApiParam(value = "offset", required = true, example = "0, 1, 2, 3...")
    private Integer offset = 0;

    @NotNull
    @ApiParam(value = "limit", required = true, example = "1, 10, 15...")
    private Integer limit = 10;
}
