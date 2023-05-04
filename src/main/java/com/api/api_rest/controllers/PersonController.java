package com.api.api_rest.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.api.api_rest.model.Person;
import com.api.api_rest.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices service;

    @RequestMapping(method = RequestMethod.GET,produces = 
    MediaType.APPLICATION_JSON_VALUE)
    public List <Person> findAll()  {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = 
    MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }
   
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete (@PathVariable(value = "id") Long id) {
        service.delete(id);
    }
   
     @RequestMapping(method = RequestMethod.POST,
     produces = MediaType.APPLICATION_JSON_VALUE,
     consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person) {
        return service.create(person);
    }

     @RequestMapping(method = RequestMethod.PUT,
     produces = MediaType.APPLICATION_JSON_VALUE,
     consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person updade(@RequestBody Person person) {
        return service.update(person);
    }

    
}
