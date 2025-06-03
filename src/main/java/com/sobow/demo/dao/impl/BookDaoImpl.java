package com.sobow.demo.dao.impl;

import com.sobow.demo.dao.BookDao;
import com.sobow.demo.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoImpl implements BookDao {
    
    private JdbcTemplate jdbcTemplate;
    
    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void create(Book book) {
        jdbcTemplate.update(
            "INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)",
            book.getIsbn(),
            book.getTitle(),
            book.getAuthorId()
        );
    }
}
