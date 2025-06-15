package com.sobow.demo.services;

import com.sobow.demo.domain.Author;
import java.util.List;

public interface AuthorService {
    
    Author createAuthor(Author author);
    
    List<Author> findAll();
}
