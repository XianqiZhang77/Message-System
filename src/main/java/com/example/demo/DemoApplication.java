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

	/*
	@PostMapping("/hello")
	public String hello(@RequestParam(value = "name", required = false) String name,
						@RequestBody(required = false) String body) {
		return String.format("Hello %s %s!", name, body);
	}
	 */


	@GetMapping(path="/hello/{name}/{age}")
	public String hello(@PathVariable("name") String name, @PathVariable("age") String age) {

		String msg = String.format("%s is %s years old", name, age);
		return msg;
	}

}

//@Request