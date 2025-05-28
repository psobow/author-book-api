package com.sobow.demo;

import com.sobow.demo.beans.services.ColourPrinter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DemoApplication implements CommandLineRunner {
	private ColourPrinter colourPrinter;
	
	public DemoApplication(ColourPrinter colourPrinter){
		this.colourPrinter = colourPrinter;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("-".repeat(20));
		log.info(colourPrinter.print());

	}
}
