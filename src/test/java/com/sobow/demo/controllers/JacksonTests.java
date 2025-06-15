package com.sobow.demo.controllers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sobow.demo.TestDataUtil;
import com.sobow.demo.domain.Author;
import com.sobow.demo.domain.Book;
import org.junit.jupiter.api.Test;

public class JacksonTests {

    @Test
    public void testThatObjectMapperCanCreateJsonFromObject() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        
        Author author = TestDataUtil.createTestAuthorA();
        Book book = TestDataUtil.createTestBookA(author);
        
        String result = objectMapper.writeValueAsString(book);
        
        assertThat(result).isEqualTo(
            "{\"isbn\":\"978-1\",\"title\":\"Witcher\",\"author\":{\"id\":null,\"name\":\"Steve\",\"age\":80}}");
    }
    
    @Test
    public void testThatObjectMapperCanCreateJavaObjectFromJSONObject() throws JsonProcessingException {
        Author author = TestDataUtil.createTestAuthorA();
        Book book = TestDataUtil.createTestBookA(author);
        
        ObjectMapper objectMapper = new ObjectMapper();
        
        String json = "{\"isbn\":\"978-1\",\"title\":\"Witcher\",\"author\":{\"id\":null,\"name\":\"Steve\",\"age\":80}}";
        
        Book result = objectMapper.readValue(json,Book.class);
        
        assertThat(result).isEqualTo(book);
    }
}
