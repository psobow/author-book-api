package com.sobow.library;

import com.sobow.library.domain.Author;
import com.sobow.library.domain.Book;
import com.sobow.library.domain.dto.AuthorDto;
import com.sobow.library.domain.dto.BookDto;

public final class TestDataUtil {
    
    private TestDataUtil() {
    }
    
    public static Author createTestAuthorA() {
        return Author.builder()
                     .name("Steve")
                     .age(80)
                     .build();
    }
    
    public static AuthorDto createTestAuthorDto() {
        return AuthorDto.builder()
                        .name("Steve")
                        .age(80)
                        .build();
    }
    
    public static Author createTestAuthorB() {
        return Author.builder()
                     .name("Dave")
                     .age(40)
                     .build();
    }
    
    public static Author createTestAuthorC() {
        return Author.builder()
                     .name("Bob")
                     .age(30)
                     .build();
    }
    
    public static Book createTestBookA(Author author) {
        return Book.builder()
                   .isbn("978-1")
                   .title("Witcher")
                   .author(author)
                   .build();
    }
    
    public static Book createTestBookB(Author author) {
        return Book.builder()
                   .isbn("978-2")
                   .title("Witcher 2")
                   .author(author)
                   .build();
    }
    
    public static BookDto createTestBookDto(AuthorDto author) {
        return BookDto.builder()
                      .isbn("978-1")
                      .title("Witcher")
                      .author(author)
                      .build();
    }
}
