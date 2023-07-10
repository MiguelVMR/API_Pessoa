package com.api.api_rest.services;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import com.api.api_rest.controllers.PersonController;
import com.api.api_rest.data.vo.v1.PersonVO;
import com.api.api_rest.exceptions.RequiredObjectIsNullException;
import com.api.api_rest.exceptions.ResourceNotFoundException;
import com.api.api_rest.mapper.DozerMapper;
import com.api.api_rest.model.Person;
import com.api.api_rest.repositories.PersonRepository;

@Service

public class PersonServices {
    
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonVO> findAll() {
        logger.info("Find all person");

        var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons.stream()
        .forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;
    }

    public PersonVO findById(Long id) {

        logger.info("Find one person");
    
        var entity= repository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO person) {
        
        if(person == null) throw new RequiredObjectIsNullException();

        logger.info("Create one person");
        var entity= DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO person) {
        
        if(person == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one person");
       var entity = repository.findById(person.getKey())
        .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddres(person.getAddres());
        entity.setGender(person.getGender()); 
        var vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }
    public void delete(Long id) {
        var entity = repository.findById(id)

        .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
        logger.info("Deleting one person");
        
        repository.delete(entity);
    }


}