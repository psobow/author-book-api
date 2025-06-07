package com.sobow.demo.dao.impl;

import static com.sobow.demo.TestDataUtil.createTestAuthor;
import static com.sobow.demo.TestDataUtil.createTestBook;
import static org.assertj.core.api.Assertions.assertThat;

import com.sobow.demo.dao.AuthorDao;
import com.sobow.demo.domain.Author;
import com.sobow.demo.domain.Book;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
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
        Author author = createTestAuthor();
        Book book = createTestBook();
        authorDao.create(author);
        book.setAuthorId(author.getId());
        
        underTest.create(book);
        Optional<Book> result = underTest.findOne(book.getIsbn());
        
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }
}
