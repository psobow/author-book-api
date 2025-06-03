package com.sobow.demo.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoImpl {
    
    private JdbcTemplate jdbcTemplate;
    
    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
