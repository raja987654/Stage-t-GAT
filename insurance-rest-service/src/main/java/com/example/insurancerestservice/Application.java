package com.example.insurancerestservice;

import com.example.insurancerestservice.entity.Driver;
import com.example.insurancerestservice.entity.Quote;
import com.example.insurancerestservice.repository.DriverRepository;
import com.example.insurancerestservice.repository.QuoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "driverInit")
    CommandLineRunner driverInit(DriverRepository driverRepository) {
        return args -> {
            System.out.println("Driver repository initialized.");
        };
    }

    @Bean(name = "quoteInit")
    CommandLineRunner quoteInit(QuoteRepository quoteRepository) {
        return args -> {
            System.out.println("Quote repository initialized.");
        };
    }
}
