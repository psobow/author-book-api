package com.sobow.demo.services;

import com.sobow.demo.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    
    Book save(String isbn, Book book);
    
    boolean isExists(String isbn);
    
    List<Book> findAll();
    
    Optional<Book> findOne(String isbn);
    
    Book partialUpdate(String isbn, Book book);
    
    void delete(String isbn);
}
