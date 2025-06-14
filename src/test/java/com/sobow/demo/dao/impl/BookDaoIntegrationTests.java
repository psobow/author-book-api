package com.sobow.demo.dao.impl;

import static com.sobow.demo.TestDataUtil.createTestAuthorA;
import static com.sobow.demo.TestDataUtil.createTestBookA;
import static com.sobow.demo.TestDataUtil.createTestBookB;
import static org.assertj.core.api.Assertions.assertThat;

import com.sobow.demo.dao.AuthorDao;
import com.sobow.demo.domain.Author;
import com.sobow.demo.domain.Book;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoIntegrationTests {
    
    private AuthorDao authorDao;
    private BookDaoImpl underTest;
    
    @Autowired
    public BookDaoIntegrationTests(BookDaoImpl underTest, AuthorDao authorDao) {
        this.underTest = underTest;
        this.authorDao = authorDao;
    }
    
    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = createTestAuthorA();
        Book book = createTestBookA();
        authorDao.create(author);
        book.setAuthorId(author.getId());
        
        underTest.create(book);
        Optional<Book> result = underTest.findOne(book.getIsbn());
        
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }
    
    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        Author author = createTestAuthorA();
        authorDao.create(author);
        
        Book bookA = createTestBookA();
        bookA.setAuthorId(author.getId());
        underTest.create(bookA);
        
        Book bookB = createTestBookB();
        bookB.setAuthorId(author.getId());
        underTest.create(bookB);
        
        List<Book> result = underTest.findAll();
        
        assertThat(result).hasSize(2)
                          .containsExactly(bookA, bookB);
    }
    
    @Test
    public void testThatBookCanBeUpdated() {
        Author author = createTestAuthorA();
        authorDao.create(author);
        
        Book bookA = createTestBookA();
        bookA.setAuthorId(author.getId());
        underTest.create(bookA);
        
        bookA.setTitle("UPDATED");
        underTest.update(bookA.getIsbn(), bookA);
        
        Optional<Book> result = underTest.findOne(bookA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);
    }
}
