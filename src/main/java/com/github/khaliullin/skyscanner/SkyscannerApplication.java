package com.github.khaliullin.skyscanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SkyscannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkyscannerApplication.class, args);
	}

}
