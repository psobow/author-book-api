package com.sobow.library.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sobow.library.TestDataUtil;
import com.sobow.library.domain.Author;
import com.sobow.library.services.AuthorService;
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
        mockMvc.perform(MockMvcRequestBuilders.get("/authors"))
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk());
    }
    
    @Test
    public void testThatFindAllAuthorsReturnsListOfAuthors() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        authorService.save(author);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/authors"))
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].id")
                                               .isNumber())
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].name")
                                               .value(author.getName()))
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].age")
                                               .value(author.getAge()));
    }
    
    @Test
    public void testThatFindOneAuthorReturnsHttpStatus200WhenAuthorExists() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        authorService.save(author);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/authors/" + author.getId()))
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk());
    }
    
    @Test
    public void testThatFindOneAuthorReturnsHttpStatus404WhenAuthorNotExists() throws Exception {
        
        mockMvc.perform(MockMvcRequestBuilders.get("/authors/99999"))
               .andExpect(MockMvcResultMatchers.status()
                                               .isNotFound());
    }
    
    @Test
    public void testThatFindOneAuthorReturnsAuthorWhenExists() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        authorService.save(author);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/authors/" + author.getId()))
               .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                                               .value(author.getId()))
               .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                                               .value(author.getName()))
               .andExpect(MockMvcResultMatchers.jsonPath("$.age")
                                               .value(author.getAge()));
    }
    
    @Test
    public void testThatFullUpdateAuthorReturnsHttpStatus404WhenAuthorNotExists() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        String authorJson = objectMapper.writeValueAsString(author);
        
        mockMvc.perform(MockMvcRequestBuilders.put("/authors/99999")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(authorJson))
               .andExpect(MockMvcResultMatchers.status()
                                               .isNotFound());
    }
    
    @Test
    public void testThatFullUpdateAuthorReturnsHttpStatus200WhenAuthorExists() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        authorService.save(author);
        String authorJson = objectMapper.writeValueAsString(author);
        
        mockMvc.perform(MockMvcRequestBuilders.put("/authors/" + author.getId())
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(authorJson))
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk());
    }
    
    @Test
    public void testThatFullUpdateUpdatesExistingAuthor() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        authorService.save(author);
        
        Author newAuthor = TestDataUtil.createTestAuthorB();
        newAuthor.setId(author.getId());
        String newAuthorJson = objectMapper.writeValueAsString(newAuthor);
        
        mockMvc.perform(MockMvcRequestBuilders.put("/authors/" + author.getId())
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(newAuthorJson))
               .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                                               .value(newAuthor.getId()))
               .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                                               .value(newAuthor.getName()))
               .andExpect(MockMvcResultMatchers.jsonPath("$.age")
                                               .value(newAuthor.getAge()));
    }
    
    @Test
    public void testThatPartialUpdateAuthorReturnsHttpStatus404WhenAuthorNotExists() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        String authorJson = objectMapper.writeValueAsString(author);
        
        mockMvc.perform(MockMvcRequestBuilders.patch("/authors/99999")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(authorJson))
               .andExpect(MockMvcResultMatchers.status()
                                               .isNotFound());
    }
    
    @Test
    public void testThatPartialUpdateAuthorReturnsHttpStatus200WhenAuthorExists() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        authorService.save(author);
        String authorJson = objectMapper.writeValueAsString(author);
        
        mockMvc.perform(MockMvcRequestBuilders.patch("/authors/" + author.getId())
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(authorJson))
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk());
    }
    
    @Test
    public void testThatPartialUpdateAuthorReturnsUpdatedAuthor() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        authorService.save(author);
        
        author.setName("UPDATED");
        String authorJson = objectMapper.writeValueAsString(author);
        
        mockMvc.perform(MockMvcRequestBuilders.patch("/authors/" + author.getId())
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(authorJson))
               .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                                               .value(author.getId()))
               .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                                               .value(author.getName()))
               .andExpect(MockMvcResultMatchers.jsonPath("$.age")
                                               .value(author.getAge()));
    }
    
    @Test
    public void testThatDeleteAuthorReturnsHttpStatus204ForNonExistingAuthor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/authors/9999")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status()
                                               .isNoContent());
    }
    
    @Test
    public void testThatDeleteAuthorReturnsHttpStatus204ForExistingAuthor() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        authorService.save(author);
        
        mockMvc.perform(MockMvcRequestBuilders.delete("/authors/" + author.getId())
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status()
                                               .isNoContent());
    }
}
