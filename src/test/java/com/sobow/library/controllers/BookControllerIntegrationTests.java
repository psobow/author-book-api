package com.sobow.library.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sobow.library.TestDataUtil;
import com.sobow.library.domain.Book;
import com.sobow.library.services.BookService;
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
public class BookControllerIntegrationTests {
    
    private MockMvc mockMvc;
    private BookService bookService;
    private ObjectMapper objectMapper;
    
    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, BookService bookService) {
        this.mockMvc = mockMvc;
        this.bookService = bookService;
        this.objectMapper = new ObjectMapper();
    }
    
    @Test
    public void testThatCreateBookReturnsHttpStatus201Created() throws Exception {
        Book book = TestDataUtil.createTestBookA(null);
        String bookJson = objectMapper.writeValueAsString(book);
        
        mockMvc.perform(MockMvcRequestBuilders.put("/books/" + book.getIsbn())
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(bookJson))
               .andExpect(MockMvcResultMatchers.status()
                                               .isCreated());
    }
    
    @Test
    public void testThatCreateBookReturnsCreatedBook() throws Exception {
        Book book = TestDataUtil.createTestBookA(null);
        String bookJson = objectMapper.writeValueAsString(book);
        
        mockMvc.perform(MockMvcRequestBuilders.put("/books/" + book.getIsbn())
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(bookJson))
               .andExpect(MockMvcResultMatchers.jsonPath("$.isbn")
                                               .value(book.getIsbn()))
               .andExpect(MockMvcResultMatchers.jsonPath("$.title")
                                               .value(book.getTitle()));
    }
    
    @Test
    public void testThatFindAllBooksReturnsHttpStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk());
    }
    
    @Test
    public void testThatFindAllBooksReturnsListOfBooks() throws Exception {
        Book book = TestDataUtil.createTestBookA(null);
        bookService.save(book.getIsbn(), book);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].isbn")
                                               .value(book.getIsbn()))
               .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].title")
                                               .value(book.getTitle()));
    }
    
    @Test
    public void testThatFindOneBookReturnsHttpStatus200WhenBookExists() throws Exception {
        Book book = TestDataUtil.createTestBookA(null);
        bookService.save(book.getIsbn(), book);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/books/" + book.getIsbn())
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk());
    }
    
    @Test
    public void testThatFindOneBookReturnsHttpStatus404WhenBookNotExists() throws Exception {
        
        mockMvc.perform(MockMvcRequestBuilders.get("/books/978-1")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status()
                                               .isNotFound());
    }
    
    @Test
    public void testThatUpdateBookReturnsHttpStatus200() throws Exception {
        Book book = TestDataUtil.createTestBookA(null);
        bookService.save(book.getIsbn(), book);
        
        Book newBook = TestDataUtil.createTestBookB(null);
        newBook.setIsbn(book.getIsbn());
        String newBookJson = objectMapper.writeValueAsString(newBook);
        
        mockMvc.perform(MockMvcRequestBuilders.put("/books/" + book.getIsbn())
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(newBookJson))
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk());
    }
    
    @Test
    public void testThatUpdateBookReturnsUpdatedBook() throws Exception {
        Book book = TestDataUtil.createTestBookA(null);
        bookService.save(book.getIsbn(), book);
        
        Book newBook = TestDataUtil.createTestBookB(null);
        newBook.setIsbn(book.getIsbn());
        String newBookJson = objectMapper.writeValueAsString(newBook);
        
        mockMvc.perform(MockMvcRequestBuilders.put("/books/" + book.getIsbn())
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(newBookJson))
               .andExpect(MockMvcResultMatchers.jsonPath("$.isbn")
                                               .value(newBook.getIsbn()))
               .andExpect(MockMvcResultMatchers.jsonPath("$.title")
                                               .value(newBook.getTitle()));
    }
    
    @Test
    public void testThatPartialUpdateBookReturnsHttpStatus200() throws Exception {
        Book book = TestDataUtil.createTestBookA(null);
        bookService.save(book.getIsbn(), book);
        
        String bookJson = objectMapper.writeValueAsString(book);
        
        mockMvc.perform(MockMvcRequestBuilders.patch("/books/" + book.getIsbn())
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(bookJson))
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk());
    }
    
    @Test
    public void testThatPartialUpdateBookReturnsUpdatedBook() throws Exception {
        Book book = TestDataUtil.createTestBookA(null);
        bookService.save(book.getIsbn(), book);
        
        book.setTitle("UPDATED");
        String bookJson = objectMapper.writeValueAsString(book);
        
        mockMvc.perform(MockMvcRequestBuilders.patch("/books/" + book.getIsbn())
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(bookJson))
               .andExpect(MockMvcResultMatchers.jsonPath("$.isbn")
                                               .value(book.getIsbn()))
               .andExpect(MockMvcResultMatchers.jsonPath("$.title")
                                               .value(book.getTitle()));
    }
    
    @Test
    public void testThatDeleteBookReturnsHttpStatus204ForNonExistingBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/999-999-999")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status()
                                               .isNoContent());
    }
    
    @Test
    public void testThatDeleteBookReturnsHttpStatus204ForExistingBook() throws Exception {
        Book book = TestDataUtil.createTestBookA(null);
        bookService.save(book.getIsbn(), book);
        
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/" + book.getIsbn())
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status()
                                               .isNoContent());
    }
}
