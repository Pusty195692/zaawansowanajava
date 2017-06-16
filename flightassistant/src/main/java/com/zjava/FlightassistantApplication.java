package com.zjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FlightassistantApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightassistantApplication.class, args);
	}
}
