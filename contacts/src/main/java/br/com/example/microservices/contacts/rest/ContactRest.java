package br.com.example.microservices.contacts.rest;

import br.com.example.microservices.contacts.entity.Contact;
import br.com.example.microservices.contacts.exception.ContactException;
import br.com.example.microservices.contacts.service.ContactService;
import br.com.example.microservices.contacts.service.params.ContactFindAllParams;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_OK;

/**
 * Created by Guilherme on 09/09/2018.
 */
@Api
@RestController
@RequestMapping("contact")
public class ContactRest {

    @Autowired
    private ContactService contactService;

    @ApiOperation(
        value = "Get Contact by ID.",
        notes = "Get Contact by ID."
    )
    @RequestMapping(
        path = "{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
        @ApiResponse(code = SC_OK, message = "Contact successfully found."),
        @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "Some error occurred while processing the request.")
    })
    public Contact findById(
        @PathVariable
        @ApiParam(value = "Contact ID.", required = true, example = "1, 2, 3...")
        Long id
    ) throws ContactException {
        return contactService.findById(id).get();
    }

    @ApiOperation(
        value = "List all contacts.",
        notes = "List all contacts by pagination."
    )
    @RequestMapping(
        path = "findAll",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
        @ApiResponse(code = SC_OK, message = "contacts successfully found."),
        @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "Some error occurred while processing the request.")
    })
    public Page<Contact> findAll(
        @Valid
        @ModelAttribute
        ContactFindAllParams params
    ) throws ContactException {
        Page<Contact> page = contactService.findAll(params);
        return page;
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
        value = "Delete an Contact.",
        notes = "Delete an Contact by ID."
    )
    @RequestMapping(
        path = "delete/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
        @ApiResponse(code = SC_OK, message = "Contact successfully removed."),
        @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "Some error occurred while processing the request.")
    })
    public void delete(
        @PathVariable
        @ApiParam(value = "Contact ID.", required = true, example = "1, 2, 3...")
        Long id
    ) throws ContactException {
        contactService.delete(contactService.findById(id).get());
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
        value = "Upadate/Insert an Contact.",
        notes = "Upadate/Insert an Contact by JSON data."
    )
    @RequestMapping(
        path = "save",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
        @ApiResponse(code = SC_OK, message = "Contact successfully updated/inserted."),
        @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = "Some error occurred while processing the request.")
    })
    public Contact save(
        @RequestBody
        @ApiParam(value = "JSON with Contact data.", required = true)
        Contact contact
    ) throws Exception {
        return contactService.save(contact);
    }
}
