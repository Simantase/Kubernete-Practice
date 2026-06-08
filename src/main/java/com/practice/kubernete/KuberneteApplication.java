package com.practice.kubernete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KuberneteApplication {

	@GetMapping
	public String message() {
		return "Welcome to Bangalore";
	}

	public static void main(String[] args) {

		SpringApplication.run(KuberneteApplication.class, args);
		System.out.println("Application has started");
	}

}
