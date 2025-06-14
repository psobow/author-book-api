package com.sobow.demo.repositories;

import static com.sobow.demo.TestDataUtil.createTestAuthorA;
import static com.sobow.demo.TestDataUtil.createTestBookA;
import static com.sobow.demo.TestDataUtil.createTestBookB;
import static org.assertj.core.api.Assertions.assertThat;

import com.sobow.demo.domain.Author;
import com.sobow.demo.domain.Book;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTests {
    
    private BookRepository underTest;
    
    @Autowired
    public BookRepositoryIntegrationTests(BookRepository underTest) {
        this.underTest = underTest;
    }
    
    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = createTestAuthorA();
        Book book = createTestBookA(author);
        
        underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());
        
        assertThat(result).isPresent();
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        Author author = createTestAuthorA();
        Book bookA = createTestBookA(author);
        underTest.save(bookA);
        Book bookB = createTestBookB(author);
        underTest.save(bookB);

        Iterable<Book> result = underTest.findAll();

        assertThat(result).hasSize(2);
    }
//
//    @Test
//    public void testThatBookCanBeUpdated() {
//        Author author = createTestAuthorA();
//        authorDao.create(author);
//
//        Book bookA = createTestBookA();
//        bookA.setAuthorId(author.getId());
//        underTest.create(bookA);
//
//        bookA.setTitle("UPDATED");
//        underTest.update(bookA.getIsbn(), bookA);
//
//        Optional<Book> result = underTest.findOne(bookA.getIsbn());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(bookA);
//    }
//
//    @Test
//    public void testThatBookCanBeDeleted() {
//        Author author = createTestAuthorA();
//        authorDao.create(author);
//
//        Book bookA = createTestBookA();
//        bookA.setAuthorId(author.getId());
//        underTest.create(bookA);
//
//        underTest.delete(bookA.getIsbn());
//
//        Optional<Book> result = underTest.findOne(bookA.getIsbn());
//        assertThat(result).isEmpty();
//    }
}
