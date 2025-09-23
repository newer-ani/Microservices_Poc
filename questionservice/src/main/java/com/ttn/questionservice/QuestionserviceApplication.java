package com.ttn.questionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class QuestionserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionserviceApplication.class, args);
	}

}
