package com.sobow.demo.controllers;

import com.sobow.demo.domain.Author;
import com.sobow.demo.domain.dto.AuthorDto;
import com.sobow.demo.mappers.Mapper;
import com.sobow.demo.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {
    
    private AuthorService authorService;
    private Mapper<Author, AuthorDto> authorMapper;
    
    public AuthorController(AuthorService authorService, Mapper<Author, AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }
    
    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        Author author = authorMapper.mapFromDto(authorDto);
        Author savedAuthor = authorService.createAuthor(author);
        return new ResponseEntity<>(authorMapper.mapToDto(savedAuthor), HttpStatus.CREATED);
    }
}
