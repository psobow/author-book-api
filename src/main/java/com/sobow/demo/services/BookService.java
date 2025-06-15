package com.sobow.demo.services;

import com.sobow.demo.domain.Book;
import java.util.List;

public interface BookService {
    
    Book createBook(String isbn, Book book);
    
    boolean isNewBook(String isbn);
    
    List<Book> findAll();
}
