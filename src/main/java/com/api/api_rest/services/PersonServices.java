package com.api.api_rest.services;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.api_rest.exceptions.ResourceNotFoundException;
import com.api.api_rest.model.Person;
import com.api.api_rest.repositories.PersonRepository;

@Service

public class PersonServices {
    
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll() {
        logger.info("Find all person");

        return repository.findAll();
    }

    public Person findById(Long id) {

        logger.info("Find one person");
    
        return repository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
    }

    public Person create(Person person) {
        
        logger.info("Create one person");

        return repository.save(person);
    }

    public Person update(Person person) {
        
        logger.info("Updating one person");
       var entity = repository.findById(person.getId())
        .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddres(person.getAddres());
        entity.setGender(person.getGender()); 
        return repository.save(person);
    }
    public void delete(Long id) {
        var entity = repository.findById(id)

        .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
        logger.info("Deleting one person");
        
        repository.delete(entity);
    }


}