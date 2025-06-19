package com.sobow.demo.services;

import com.sobow.demo.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    
    Book createBook(String isbn, Book book);
    
    boolean isNewBook(String isbn);
    
    List<Book> findAll();
    
    Optional<Book> findOne(String isbn);
}
