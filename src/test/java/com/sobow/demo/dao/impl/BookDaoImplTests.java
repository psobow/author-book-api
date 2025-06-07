package com.sobow.demo.dao.impl;

import static com.sobow.demo.TestDataUtil.createTestBook;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import com.sobow.demo.dao.impl.BookDaoImpl.BookRowMapper;
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
        
        Book book = createTestBook();
        
        underTest.create(book);
        
        verify(jdbcTemplate).update(
            eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
            eq("978-1"),
            eq("Witcher"),
            eq(1L)
        );
    }
    
    @Test
    public void testThatFindOneGeneratesCorrectSql() {
        underTest.findOne("978-1");
        verify(jdbcTemplate).query(
            eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
            any(BookRowMapper.class),
            eq("978-1"));
    }
}
