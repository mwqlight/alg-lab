package com.alglab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AlgLabBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlgLabBackendApplication.class, args);
    }

}