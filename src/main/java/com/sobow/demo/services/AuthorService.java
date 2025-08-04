package com.sobow.demo.services;

import com.sobow.demo.domain.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    
    Author save(Author author);
    
    List<Author> findAll();
    
    Optional<Author> findOne(Long id);
    
    boolean existsById(Long id);
    
    Author partialUpdate(Author author);
    
    void delete(Long id);
}
