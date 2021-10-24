package com.bits.cpad.auctionsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AuctionSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuctionSystemApplication.class, args);
    }

}
