package com.sobow.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sobow.demo.TestDataUtil;
import com.sobow.demo.domain.Author;
import com.sobow.demo.services.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTests {
    
    private MockMvc mockMvc;
    private AuthorService authorService;
    private ObjectMapper objectMapper;
    
    @Autowired
    public AuthorControllerIntegrationTests(MockMvc mockMvc, AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.authorService = authorService;
        objectMapper = new ObjectMapper();
    }
    
    @Test
    public void testThatCreateAuthorSuccessfullyReturnsHttp201Created() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        String authorJson = objectMapper.writeValueAsString(author);
        
        mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(authorJson))
               .andExpect(MockMvcResultMatchers.status()
                                               .isCreated());
    }
    
    @Test
    public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        String authorJson = objectMapper.writeValueAsString(author);
        
        mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(authorJson))
               .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                                               .isNumber())
               .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                                               .value(author.getName()))
               .andExpect(MockMvcResultMatchers.jsonPath("$.age")
                                               .value(author.getAge()));
    }
    
    @Test
    public void testThatFindAllAuthorsReturnsHttpStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/authors")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk());
    }
    
    @Test
    public void testThatFindAllAuthorsReturnsListOfAuthors() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        authorService.createAuthor(author);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/authors")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].id")
                                               .isNumber())
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].name")
                                               .value(author.getName()))
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].age")
                                               .value(author.getAge()));
    }
}
