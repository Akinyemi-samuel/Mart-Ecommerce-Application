package com.samfrosh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan()
@SpringBootApplication
public class MartApplication {

	public static void main(String[] args) {
		SpringApplication.run(MartApplication.class, args);
	}

}
