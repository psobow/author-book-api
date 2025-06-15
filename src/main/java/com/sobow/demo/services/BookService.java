package com.sobow.demo.services;

import com.sobow.demo.domain.Book;

public interface BookService {
    
    Book createBook(String isbn, Book book);
    
    boolean isNewBook(String isbn);
}
