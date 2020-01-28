package com.rbs.prime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PrimeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimeAppApplication.class, args);
	}

}
