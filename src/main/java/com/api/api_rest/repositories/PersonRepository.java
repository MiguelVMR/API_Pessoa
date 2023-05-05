package com.api.api_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.api.api_rest.model.Person;

public interface PersonRepository extends JpaRepository <Person,Long> {
    
}
