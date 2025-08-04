package com.sobow.demo.services;

import com.sobow.demo.domain.Author;
import com.sobow.demo.repositories.AuthorRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    
    private AuthorRepository authorRepository;
    
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    
    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }
    
    @Override
    public List<Author> findAll() {
        return StreamSupport.stream(authorRepository.findAll()
                                                    .spliterator(), false)
                            .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Author> findOne(Long id) {
        return authorRepository.findById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return authorRepository.existsById(id);
    }
    
    @Override
    public Author partialUpdate(Author author) {
        return authorRepository.findById(author.getId())
                               .map(existingAuthor -> {
                                   Optional.ofNullable(author.getName())
                                           .ifPresent(existingAuthor::setName);
                                   Optional.ofNullable(author.getAge())
                                           .ifPresent(existingAuthor::setAge);
                                   return authorRepository.save(existingAuthor);
                               })
                               .orElseThrow(() -> new RuntimeException("Author does not exist"));
    }
    
    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
