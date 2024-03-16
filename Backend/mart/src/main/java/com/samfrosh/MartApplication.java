package com.samfrosh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@ComponentScan()
@SpringBootApplication
public class MartApplication {

    public static void main(String[] args) {
        SpringApplication.run(MartApplication.class, args);
    }

}
