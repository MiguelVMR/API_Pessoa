package com.api.api_rest.services;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.api_rest.data.vo.v1.PersonVO;
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

        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {

        logger.info("Find one person");
    
        var entity= repository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person) {
        
        logger.info("Create one person");
        var entity= DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class);
        return vo;
    }

    public PersonVO update(PersonVO person) {
        
        logger.info("Updating one person");
       var entity = repository.findById(person.getId())
        .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddres(person.getAddres());
        entity.setGender(person.getGender()); 
        var vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class);
        return vo;
    }
    public void delete(Long id) {
        var entity = repository.findById(id)

        .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
        logger.info("Deleting one person");
        
        repository.delete(entity);
    }


}