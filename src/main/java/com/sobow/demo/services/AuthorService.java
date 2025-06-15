package com.sobow.demo.services;

import com.sobow.demo.domain.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    
    Author createAuthor(Author author);
    
    List<Author> findAll();
    
    Optional<Author> findOne(Long id);
}
