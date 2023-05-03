package com.api.api_rest.services;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import com.api.api_rest.model.Person;

@Service

public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id) {
        
        logger.info("Find one person");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Miguel");
        person.setLastName("Vilela");
        person.setAddres("Itajubá - Minas Gerais - Brasil");
        person.setGender("Male");

        return person;
}
}