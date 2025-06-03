package com.sobow.demo.dao;

import com.sobow.demo.domain.Author;
import java.util.Optional;

public interface AuthorDao {
    
    void create(Author author);
    
    Optional<Author> findOne(long l);
}
