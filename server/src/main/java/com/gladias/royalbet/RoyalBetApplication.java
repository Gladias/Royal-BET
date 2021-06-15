package com.gladias.royalbet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RoyalBetApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoyalBetApplication.class, args);
	}

}
