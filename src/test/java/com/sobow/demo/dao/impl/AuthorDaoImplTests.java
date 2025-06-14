package com.sobow.demo.dao.impl;

import static com.sobow.demo.TestDataUtil.createTestAuthorA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import com.sobow.demo.dao.impl.AuthorDaoImpl.AuthorRowMapper;
import com.sobow.demo.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {
    
    @Mock
    private JdbcTemplate jdbcTemplate;
    
    @InjectMocks
    private AuthorDaoImpl underTest;
    
    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {
        
        Author author = createTestAuthorA();
        
        underTest.create(author);
        /*
            1. We want to verify that specific method of jdbcTemplate is called with specific set of arguments
            2. We use parameterized query it is safer because JDBC driver will treat parameters as a literal strings,
             not executable SQL's
            3.When you're verifying method calls like this, using ArgumentMatchers.eq()
                You're not executing the method, you are telling mockito:
                Please verify that this method was called with these specific arguments.
         */
        verify(jdbcTemplate).update(
            eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
            eq(1L),
            eq("Steve"),
            eq(80)
        );
    }
    
    @Test
    public void testThatFindOneGeneratesCorrectSql() {
        
        underTest.findOne(1L);
        
        verify(jdbcTemplate).query(
            eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
            any(AuthorRowMapper.class), eq(1L));
    }
    
    @Test
    public void testThatFindAllGeneratesCorrectSql() {
        underTest.findAll();
        verify(jdbcTemplate).query(eq("SELECT id, name, age FROM authors"), any(AuthorRowMapper.class));
    }
    
    @Test
    public void testThatUpdateGeneratesCorrectSql() {
        Author author = createTestAuthorA();
        underTest.update(author.getId(), author);
        verify(jdbcTemplate).update("UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?", 1L, "Steve", 80, 1L);
    }
    
    @Test
    public void testThatDeleteGeneratesCorrectSql() {
        underTest.delete(1L);
        verify(jdbcTemplate).update("DELETE FROM authors where id = ?", 1L);
    }
}
