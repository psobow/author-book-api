package com.sobow.library.controllers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sobow.library.TestDataUtil;
import com.sobow.library.domain.Author;
import com.sobow.library.domain.Book;
import org.junit.jupiter.api.Test;

public class JacksonTests {
    
    @Test
    public void testThatObjectMapperCanCreateJsonFromObject() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        
        Author author = TestDataUtil.createTestAuthorA();
        Book book = TestDataUtil.createTestBookA(author);
        
        String result = objectMapper.writeValueAsString(book);
        System.out.println(result);
        System.out.println(book);
        
        assertThat(result).isEqualTo(
            "{\"isbn\":\"978-1\",\"author\":{\"id\":null,\"name\":\"Steve\",\"age\":80}," + "\"title\":\"Witcher\"}");
    }
    
    @Test
    public void testThatObjectMapperCanCreateJavaObjectFromJSONObject() throws JsonProcessingException {
        Author author = TestDataUtil.createTestAuthorA();
        Book book = TestDataUtil.createTestBookA(author);
        
        ObjectMapper objectMapper = new ObjectMapper();
        
        String json =
            "{\"foo\":\"bar\",\"isbn\":\"978-1\",\"title\":\"Witcher\",\"author\":{\"id\":null," + "\"name\":\"Steve\","
                + "\"age\":80}}";
        
        Book result = objectMapper.readValue(json, Book.class);
        
        assertThat(result).isEqualTo(book);
    }
}
