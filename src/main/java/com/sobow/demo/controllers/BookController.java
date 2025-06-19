package com.sobow.demo.controllers;

import com.sobow.demo.domain.Book;
import com.sobow.demo.domain.dto.BookDto;
import com.sobow.demo.mappers.Mapper;
import com.sobow.demo.services.BookService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BookController {
    
    private Mapper<Book, BookDto> bookMapper;
    private BookService bookService;
    
    public BookController(Mapper<Book, BookDto> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }
    
    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
        boolean isExists = bookService.isExists(isbn);
        
        Book book = bookMapper.mapFromDto(bookDto);
        Book savedBook = bookService.createBook(isbn, book);
        
        HttpStatus status = isExists ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity<>(bookMapper.mapToDto(savedBook), status);
    }
    
    @GetMapping(path = "/books")
    public List<BookDto> findAllBooks() {
        List<Book> books = bookService.findAll();
        List<BookDto> bookDtoList = books.stream()
                                         .map(bookMapper::mapToDto)
                                         .collect(Collectors.toList());
        return bookDtoList;
    }
    
    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> findOneBook(@PathVariable("isbn") String isbn) {
        Optional<Book> optionalBook = bookService.findOne(isbn);
        return optionalBook.map(book -> new ResponseEntity<>(bookMapper.mapToDto(book), HttpStatus.OK))
                           .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
