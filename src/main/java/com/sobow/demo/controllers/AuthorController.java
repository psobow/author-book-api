package com.sobow.demo.controllers;

import com.sobow.demo.domain.Author;
import com.sobow.demo.domain.dto.AuthorDto;
import com.sobow.demo.mappers.Mapper;
import com.sobow.demo.services.AuthorService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        Author savedAuthor = authorService.save(author);
        AuthorDto savedAuthorDto = authorMapper.mapToDto(savedAuthor);
        return new ResponseEntity<>(savedAuthorDto, HttpStatus.CREATED);
    }
    
    @GetMapping(path = "/authors")
    public List<AuthorDto> findAllAuthors() {
        List<Author> authors = authorService.findAll();
        List<AuthorDto> authorDtoList = authors.stream()
                                               .map(authorMapper::mapToDto)
                                               .collect(Collectors.toList());
        return authorDtoList;
    }
    
    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> findOneAuthor(@PathVariable("id") Long id) {
        Optional<Author> optionalAuthor = authorService.findOne(id);
        return optionalAuthor.map(author -> new ResponseEntity<>(authorMapper.mapToDto(author), HttpStatus.OK))
                             .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> fullUpdateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto) {
        boolean isExists = authorService.isExists(id);
        if (!isExists) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        authorDto.setId(id);
        Author savedAuthor = authorService.save(authorMapper.mapFromDto(authorDto));
        return new ResponseEntity<>(authorMapper.mapToDto(savedAuthor), HttpStatus.OK);
    }
}
