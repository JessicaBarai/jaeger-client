package com.techprimers.jaegerclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController
public class JaegerClientApplication {
	
	@GetMapping("/")
	public String welcome() {
		System.out.print("change enabled");
		return "welcome to java!";
		
	}

	public static void main(String[] args) {
		System.out.print("STARTING WITH PROPERTIES CHANGED!");
		SpringApplication.run(JaegerClientApplication.class, args);
	}

}
