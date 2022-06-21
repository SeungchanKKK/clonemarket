package com.marketkutty.marketkutty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MarketKuttyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketKuttyApplication.class, args);
    }

}
