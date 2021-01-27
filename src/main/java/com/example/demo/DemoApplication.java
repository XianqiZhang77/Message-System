package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostMapping("/hello")
	public String hello(@RequestParam(value = "name", required = false) String name,
						@RequestBody(required = false) String body) {
		return String.format("Hello %s %s!", name, body);
	}
}

//@Request