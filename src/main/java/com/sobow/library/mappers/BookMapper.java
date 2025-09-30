package com.sobow.library.mappers;

import com.sobow.library.domain.Book;
import com.sobow.library.domain.dto.BookDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<Book, BookDto> {
    
    private ModelMapper modelMapper;
    
    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    
    @Override
    public BookDto mapToDto(Book book) {
        return modelMapper.map(book, BookDto.class);
    }
    
    @Override
    public Book mapFromDto(BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }
}
