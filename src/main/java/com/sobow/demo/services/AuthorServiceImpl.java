package com.sobow.demo.services;

import com.sobow.demo.domain.Author;
import com.sobow.demo.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService{
    
    private AuthorRepository authorRepository;
    
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    
    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }
}
