package com.sobow.demo.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

public class AuthorDaoImpl {
    
    private JdbcTemplate jdbcTemplate;
    
    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
