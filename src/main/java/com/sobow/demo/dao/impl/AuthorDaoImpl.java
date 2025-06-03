package com.sobow.demo.dao.impl;

import com.sobow.demo.dao.AuthorDao;
import com.sobow.demo.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;

public class AuthorDaoImpl implements AuthorDao {
    
    private JdbcTemplate jdbcTemplate;
    
    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void create(Author author) {
        jdbcTemplate.update(
            "INSERT INTO authors (id, name, age) VALUES (?, ?, ?)",
            author.getId(),
            author.getName(),
            author.getAge());
    }
}
