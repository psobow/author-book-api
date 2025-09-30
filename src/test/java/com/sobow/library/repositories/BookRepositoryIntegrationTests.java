package com.sobow.library.repositories;

import static com.sobow.library.TestDataUtil.createTestAuthorA;
import static com.sobow.library.TestDataUtil.createTestBookA;
import static com.sobow.library.TestDataUtil.createTestBookB;
import static org.assertj.core.api.Assertions.assertThat;

import com.sobow.library.domain.Author;
import com.sobow.library.domain.Book;
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
    private AuthorRepository authorRepository;
    
    @Autowired
    public BookRepositoryIntegrationTests(BookRepository underTest, AuthorRepository authorRepository) {
        this.underTest = underTest;
        this.authorRepository = authorRepository;
    }
    
    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = createTestAuthorA();
        authorRepository.save(author);
        
        Book book = createTestBookA(author);
        underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());
        
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }
    
    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        Author author = createTestAuthorA();
        authorRepository.save(author);
        Book bookA = createTestBookA(author);
        underTest.save(bookA);
        Book bookB = createTestBookB(author);
        underTest.save(bookB);
        
        Iterable<Book> result = underTest.findAll();
        
        assertThat(result).hasSize(2)
                          .containsExactly(bookA, bookB);
    }
    
    @Test
    public void testThatBookCanBeUpdated() {
        Author author = createTestAuthorA();
        authorRepository.save(author);
        
        Book bookA = createTestBookA(author);
        underTest.save(bookA);
        
        bookA.setTitle("UPDATED");
        underTest.save(bookA);
        
        Optional<Book> result = underTest.findById(bookA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);
    }
    
    @Test
    public void testThatBookCanBeDeleted() {
        Author author = createTestAuthorA();
        authorRepository.save(author);
        
        Book bookA = createTestBookA(author);
        underTest.save(bookA);
        
        underTest.deleteById(bookA.getIsbn());
        
        Optional<Book> result = underTest.findById(bookA.getIsbn()); // because of CascadeType.ALL it also removes
        // author
        assertThat(result).isEmpty();
    }
}
