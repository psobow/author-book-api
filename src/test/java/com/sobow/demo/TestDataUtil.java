package com.sobow.demo;

import com.sobow.demo.domain.Author;
import com.sobow.demo.domain.Book;

public final class TestDataUtil {
    
    private TestDataUtil() {
    }
    
    public static Author createTestAuthorA() {
        return Author.builder()
                     .id(1L)
                     .name("Steve")
                     .age(80)
                     .build();
    }
    
    public static Author createTestAuthorB() {
        return Author.builder()
                     .id(2L)
                     .name("Dave")
                     .age(40)
                     .build();
    }
    
    public static Author createTestAuthorC() {
        return Author.builder()
                     .id(3L)
                     .name("Bob")
                     .age(30)
                     .build();
    }
    
    public static Book createTestBookA() {
        return Book.builder()
                   .isbn("978-1")
                   .title("Witcher")
                   .authorId(1L)
                   .build();
    }
    
    public static Book createTestBookB() {
        return Book.builder()
                   .isbn("978-2")
                   .title("Witcher 2")
                   .authorId(1L)
                   .build();
    }
}
