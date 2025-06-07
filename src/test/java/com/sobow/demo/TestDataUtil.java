package com.sobow.demo;

import com.sobow.demo.domain.Author;
import com.sobow.demo.domain.Book;

public final class TestDataUtil {
    
    private TestDataUtil() {
    }
    
    public static Author createTestAuthor() {
        return Author.builder()
                     .id(1L)
                     .name("Steve")
                     .age(80)
                     .build();
    }
    
    public static Book createTestBook() {
        return Book.builder()
                   .isbn("978-1")
                   .title("Witcher")
                   .authorId(1L)
                   .build();
    }
}
