package com.sobow.library.services;

import com.sobow.library.domain.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    
    Book save(String isbn, Book book);
    
    boolean existsById(String isbn);
    
    List<Book> findAll();
    
    Page<Book> findAll(Pageable pageable);
    
    Optional<Book> findOne(String isbn);
    
    Book partialUpdate(String isbn, Book book);
    
    void delete(String isbn);
}
