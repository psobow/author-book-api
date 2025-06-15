package com.sobow.demo.repositories;

import com.sobow.demo.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    
    Iterable<Author> ageLessThan(int age);
}
