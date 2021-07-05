package com.techprimers.jaegerclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JaegerClientApplication {
	
	@GetMapping("/")
	public String welcome() {
		System.out.print("change enabled");
		return "Hello from Client!";
		
	}

	public static void main(String[] args) {
		System.out.print("STARTING WITH PROPERTIES CHANGED1.2!");
		SpringApplication.run(JaegerClientApplication.class, args);
	}

}
