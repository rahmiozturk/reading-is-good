package com.getir.readingisgood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.getir.readingisgood")
public class ReadingIsGoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadingIsGoodApplication.class, args);
	}

}
