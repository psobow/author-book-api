package com.sobow.library.mappers;

import com.sobow.library.domain.Author;
import com.sobow.library.domain.dto.AuthorDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements Mapper<Author, AuthorDto> {
    
    private ModelMapper modelMapper;
    
    public AuthorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    
    @Override
    public AuthorDto mapToDto(Author author) {
        return modelMapper.map(author, AuthorDto.class);
    }
    
    @Override
    public Author mapFromDto(AuthorDto authorDto) {
        return modelMapper.map(authorDto, Author.class);
    }
}
