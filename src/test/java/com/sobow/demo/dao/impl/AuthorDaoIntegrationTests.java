package com.sobow.demo.dao.impl;

import static com.sobow.demo.TestDataUtil.createTestAuthorA;
import static com.sobow.demo.TestDataUtil.createTestAuthorB;
import static com.sobow.demo.TestDataUtil.createTestAuthorC;
import static org.assertj.core.api.Assertions.assertThat;

import com.sobow.demo.domain.Author;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoIntegrationTests {
    
    private AuthorDaoImpl underTest;
    
    @Autowired
    public AuthorDaoIntegrationTests(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }
    
    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = createTestAuthorA();
        
        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());
        
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }
    
    @Test
    public void testThatManyAuthorsCanBeCreatedAndRecalled() {
        Author authorA = createTestAuthorA();
        underTest.create(authorA);
        Author authorB = createTestAuthorB();
        underTest.create(authorB);
        Author authorC = createTestAuthorC();
        underTest.create(authorC);
        
        List<Author> result = underTest.findAll();
        
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(authorA, authorB, authorC);
    }
    
    @Test
    public void testThatAuthorCanBeUpdated() {
        Author authorA = createTestAuthorA();
        underTest.create(authorA);
        authorA.setName("UPDATED");
        
        underTest.update(authorA.getId(), authorA);
        
        Optional<Author> result = underTest.findOne(authorA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorA);
    }
    
    @Test
    public void testThatAuthorCanBeDeleted() {
        Author authorA = createTestAuthorA();
        underTest.create(authorA);
        
        underTest.delete(authorA.getId());
        
        Optional<Author> result = underTest.findOne(authorA.getId());
        assertThat(result).isEmpty();
    }
}
