package com.bidding.auction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.onlinebidding", "com.bidding"})
public class AuctionAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuctionAppApplication.class, args);
    }

}
