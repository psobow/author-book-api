package com.sobow.demo.dao.impl;

import static com.sobow.demo.TestDataUtil.createTestAuthor;
import static org.assertj.core.api.Assertions.assertThat;

import com.sobow.demo.domain.Author;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthorDaoIntegrationTests {
    
    private AuthorDaoImpl underTest;
    
    @Autowired
    public AuthorDaoIntegrationTests(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }
    
    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = createTestAuthor();
        
        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());
        
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }
}
