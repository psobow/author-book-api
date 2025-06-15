package com.sobow.demo.services;

import com.sobow.demo.domain.Book;
import com.sobow.demo.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    
    private BookRepository bookRepository;
    
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @Override
    public Book createBook(String isbn, Book book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }
    
    @Override
    public boolean isNewBook(String isbn) {
        return !bookRepository.existsById(isbn);
    }
}
