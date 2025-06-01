package com.sobow.demo;

import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@Slf4j
public class DemoApplication implements CommandLineRunner {
    private DataSource dataSource;
    
    public DemoApplication(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        
        log.info("DataSource: " + dataSource.toString());
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("select 1");
    }
}
