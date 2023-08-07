package com.api.api_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.api_rest.model.Book;


public interface BookRepository extends JpaRepository <Book,Long> {
    
}
