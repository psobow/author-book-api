package com.sobow.demo.dao;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import com.sobow.demo.dao.impl.AuthorDaoImpl;
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
        
        Author author = Author.builder()
                              .id(1L)
                              .name("Steve")
                              .age(80)
                              .build();
        
        underTest.create(author);
        /*
            1. We want to verify that specific method of jdbcTemplate is called with specific set of arguments
            2. We use parameterized query it is safer because JDBC driver will treat parameters as a literal strings, not
                executable SQL's
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
}
