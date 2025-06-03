package com.sobow.demo.dao;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import com.sobow.demo.dao.impl.BookDaoImpl;
import com.sobow.demo.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {
    
    @Mock
    private JdbcTemplate jdbcTemplate;
    
    @InjectMocks
    private BookDaoImpl underTest;
    
    @Test
    public void testThatCreateBookGeneratesCorrectSql() {
        
        Book book = Book.builder()
                        .isbn("978-1")
                        .title("Witcher")
                        .authorId(1L)
                        .build();
        
        underTest.create(book);
        
        verify(jdbcTemplate).update(
            eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
            eq("978-1"),
            eq("Witcher"),
            eq(1L)
        );
    }
}
