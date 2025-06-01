package com.sobow.demo;

import com.sobow.demo.beans.services.ColourPrinter;
import com.sobow.demo.pizza.PizzaConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DemoApplication implements CommandLineRunner {
    
    private ColourPrinter colourPrinter;
    private PizzaConfig pizzaConfig;
    
    public DemoApplication(ColourPrinter colourPrinter, PizzaConfig pizzaConfig) {
        this.colourPrinter = colourPrinter;
        this.pizzaConfig = pizzaConfig;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("-".repeat(20));
        log.info(colourPrinter.print());
        System.out.println("-".repeat(20));
        log.info(String.format("I want a %s crust pizza, with %s and %s sauce.",
                               pizzaConfig.getCrust(),
                               pizzaConfig.getTopping(),
                               pizzaConfig.getSauce()));
    }
}
